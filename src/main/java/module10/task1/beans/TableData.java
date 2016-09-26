package module10.task1.beans;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 21.09.2016.
 */
public class TableData {
    private String name;
    private int columnCount;
    private Set<ColumnData> columnDatas;
    private Set<String> primaryKeys;
    private Set<ForeignKeyInfo> foreignKeys;


    public TableData() {
        columnDatas = new LinkedHashSet<>();
        primaryKeys = new HashSet<>();
        foreignKeys = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public Set<ColumnData> getColumnDatas() {
        return columnDatas;
    }

    public void addColumnData(ColumnData columnData) {
        this.columnDatas.add(columnData);
    }

    public void addPrimaryKey(String key) {
        this.primaryKeys.add(key);
    }

    public Set<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(Set<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public void addForeignKey(ForeignKeyInfo key) {
        this.foreignKeys.add(key);
    }

    public Set<ForeignKeyInfo> getForeignKeys() {
        return foreignKeys;
    }

    public String getCreateQuery() {

        return String.format("CREATE TABLE %s ( %s );", name, columnDatas.stream()
                .map(ColumnData::getOptions)
                .collect(Collectors.joining(",")));

    }

    public String getColumnsList() {
        return columnDatas.stream()
                .map(ColumnData::getName)
                .collect(Collectors.joining(","));
    }

    public String getPrimaryKeyAlterQuery() {
        StringBuilder query = new StringBuilder();
        columnDatas.stream()
                .filter(e -> primaryKeys.contains(e.getName()))
                .forEach(column -> query.append(String.format("ALTER TABLE %s ALTER COLUMN %s %s NOT NULL;", name, column.getName(), column.getType())));
        query.append(String.format(" ALTER TABLE %s ADD PRIMARY KEY (%s);", name, primaryKeys.stream().collect(Collectors.joining(","))));
        return query.toString();
    }

    public String getForeignKeyAlterQuery() {
        StringBuilder query = new StringBuilder();
        foreignKeys.stream().forEach(key -> query.append(String.format("ALTER TABLE %s ADD CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s (%s);",
                name, key.getName(), key.getColumnName(), key.getPkTableName(), key.getPkColumnName())));

        foreignKeys.stream().forEach(key -> System.out.println(String.format("ALTER TABLE %s ADD CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s (%s);",
                name, key.getName(), key.getColumnName(), key.getPkTableName(), key.getPkColumnName())));

        return query.toString();
    }

    public String getSelectQuery() {
        return "SELECT * FROM " + name;
    }

    public String getInsertQuery() {
        return String.format("INSERT INTO %s ( %s ) VALUES ( %s );", name, getColumnsList(),
                Collections.nCopies(columnDatas.size(), "?").stream().collect(Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableData{");
        sb.append(", columnCount=").append(columnCount);
        sb.append(", columnDatas=").append(columnDatas);
        sb.append(", primaryKeys=").append(primaryKeys);
        sb.append(", foreignKeys=").append(foreignKeys);
        sb.append('}');
        return sb.toString();
    }


}
