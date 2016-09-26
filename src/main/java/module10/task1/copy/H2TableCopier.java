package module10.task1.copy;

import module10.task1.beans.ColumnData;
import module10.task1.beans.ForeignKeyInfo;
import module10.task1.beans.TableData;
import org.apache.commons.dbcp.BasicDataSource;
import org.h2.jdbc.JdbcSQLException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by user on 24.09.2016.
 */
public class H2TableCopier implements ITableCopier {
    public static final String TABLE = "TABLE";
    private static final String FKTABLE_NAME = "FKTABLE_NAME";
    private static final String FKCOLUMN_NAME = "FKCOLUMN_NAME";
    private static final String FK_NAME = "FK_NAME";
    private static final String PKTABLE_NAME = "PKTABLE_NAME";
    private static final String PKCOLUMN_NAME = "PKCOLUMN_NAME";
    private static final String KEY_SEQ = "KEY_SEQ";
    private static final String COLUMN_NAME = "COLUMN_NAME";
    private static final String TYPE_NAME = "TYPE_NAME";
    private static final String NULLABLE = "NULLABLE";
    private static final String DB_PROPERTIES_PROPERTIES = "db_properties.properties";
    public static final String MODULE10_DESTINATION_URL = "module10.destination.url";


    private ExecutorService service = Executors.newCachedThreadPool();
    private String url;
    private BasicDataSource source;
    private BasicDataSource target;

    public H2TableCopier(String url) {
        this.url = url;
        source = new BasicDataSource();
        source.setUrl(url);
        source.setMaxActive(5);
        source.setMaxIdle(2);


        target = new BasicDataSource();
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_PROPERTIES)) {
            properties.load(inputStream);
            target.setUrl(properties.getProperty(MODULE10_DESTINATION_URL));
            target.setMaxActive(5);
            target.setMaxIdle(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copy(Order order) {
        List<TableData> data = getTableData();
        creteTables(data);
        insert(data, order);
        addConstrains(data);
    }

    public List<TableData> getTableData() {
        List<TableData> data = new ArrayList<>();
        try (Connection metaConnection = getSourceConnection(url)) {
            DatabaseMetaData metaData = metaConnection.getMetaData();
            data = buildData(metaData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return data.stream().sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList());
        }
    }

    private List<TableData> buildData(DatabaseMetaData metaData) {
        List<TableData> data = new ArrayList<>();
        try {
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{TABLE});

            while (tables.next()) {

                TableData tableData = new TableData();
                String tableName = tables.getString(ColumnData.ColumnNames.TABLE_NAME.toString());
                tableData.setName(tableName);

                ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, null, tableName);
                while (primaryKeysResultSet.next()) {
                    tableData.addPrimaryKey(primaryKeysResultSet.getString(ColumnData.ColumnNames.COLUMN_NAME.toString()));
                }

                buildReferences(metaData, tableData, tableName);

                buildColumnData(metaData, tableData, tableName);

                data.add(tableData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return data;
        }
    }

    private void buildReferences(DatabaseMetaData metaData, TableData tableData, String tableName) throws SQLException {
        ResultSet referencesResultSet = metaData.getImportedKeys(null, null, tableName);

        while (referencesResultSet.next()) {
            String fkTableName = referencesResultSet.getString(FKTABLE_NAME);
            String fkColumnName = referencesResultSet.getString(FKCOLUMN_NAME);
            String name = referencesResultSet.getString(FK_NAME);
            String pkTableName = referencesResultSet.getString(PKTABLE_NAME);
            String pkColumnName = referencesResultSet.getString(PKCOLUMN_NAME);
            int keySequence = referencesResultSet.getInt(KEY_SEQ);

            ForeignKeyInfo foreignKeyInfo = new ForeignKeyInfo(fkTableName, fkColumnName, keySequence);
            foreignKeyInfo.setName(name);
            foreignKeyInfo.setPkColumnName(pkColumnName);
            foreignKeyInfo.setPkTableName(pkTableName);
            tableData.addForeignKey(foreignKeyInfo);

        }
    }

    private void buildColumnData(DatabaseMetaData metaData, TableData tableData, String tableName) throws SQLException {
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        ResultSetMetaData resultSetMetaData = columns.getMetaData();
        while (columns.next()) {
            metaData.getPrimaryKeys(null, null, tableName);

            ColumnData columnData = new ColumnData();
            columnData.setName(columns.getString(COLUMN_NAME));
            columnData.setType(columns.getString(TYPE_NAME));
            columnData.setAutoincrement(resultSetMetaData.isAutoIncrement(1));
            columnData.setNotNull(columns.getInt(NULLABLE) != ResultSetMetaData.columnNullable);
            tableData.addColumnData(columnData);
        }
    }


    public void creteTables(List<TableData> data) {
        try (Statement statement = getTargetConnection().createStatement()) {
            data.stream().forEach(table -> {
                try {
                    statement.execute(table.getCreateQuery());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void insert(List<TableData> data, Order order) {
        List<Future<String>> results = new ArrayList<>();
        for (TableData tableData : data) {
            results.add(service.submit(() -> {
                        try (Connection targetConnection = getTargetConnection()) {

                            try (PreparedStatement targetStatement = targetConnection.prepareStatement(tableData.getInsertQuery())) {

                                try (Connection sourceConnection = getSourceConnection(url)) {
                                    try (Statement sourceStatement = sourceConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY)) {
                                        try (ResultSet fromSource = sourceStatement.executeQuery(tableData.getSelectQuery())) {
                                            int columnsCount = fromSource.getMetaData().getColumnCount();

                                            switch (order) {
                                                case REVERSE:
                                                    fromSource.afterLast();
                                                    while (fromSource.previous()) {
                                                        for (int i = 1; i <= columnsCount; i++) {
                                                            targetStatement.setObject(i, fromSource.getObject(i));
                                                        }
                                                        targetStatement.addBatch();
                                                    }
                                                    break;
                                                case DIRECT:
                                                default:

                                                    fromSource.setFetchSize(1000);
                                                    while (fromSource.next()) {
                                                        for (int i = 1; i <= columnsCount; i++) {
                                                            targetStatement.setObject(i, fromSource.getObject(i));
                                                        }
                                                        targetStatement.addBatch();
                                                    }
                                            }
                                            targetStatement.executeBatch();

                                        } catch (JdbcSQLException sd) {
                                            System.out.println(tableData + " failed");
                                        }
                                    }
                                }
                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            return "inserted";
                        }
                    }
            ));
        }

        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
            for (Future f : results) {
                System.out.println(f.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    public void addConstrains(List<TableData> data) {
        for (TableData tableData : data) {
            try (Connection targetConnection = getTargetConnection()) {
                try (PreparedStatement preparedStatement = targetConnection.prepareStatement(tableData.getPrimaryKeyAlterQuery())) {
                    preparedStatement.execute();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        for (TableData tableData : data) {
            try (Connection targetConnection = getTargetConnection()) {
                try (PreparedStatement preparedStatement = targetConnection.prepareStatement(tableData.getForeignKeyAlterQuery())) {
                    preparedStatement.execute();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    public synchronized Connection getSourceConnection(String url) throws SQLException {

        return source.getConnection();

    }

    protected synchronized Connection getTargetConnection() throws SQLException {
        return target.getConnection();
    }


}
