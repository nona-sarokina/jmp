package module10.task1.beans;

/**
 * Created by user on 24.09.2016.
 */
public class ForeignKeyInfo {
    String tableName;
    String columnName;
    String name;
    int sequence;

    String pkTableName;
    String pkColumnName;

    public ForeignKeyInfo(String tableName, String columnName, int sequence) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.sequence = sequence;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForeignKeyInfo that = (ForeignKeyInfo) o;

        if (sequence != that.sequence) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pkTableName != null ? !pkTableName.equals(that.pkTableName) : that.pkTableName != null) return false;
        return pkColumnName != null ? pkColumnName.equals(that.pkColumnName) : that.pkColumnName == null;

    }

    @Override
    public int hashCode() {
        int result = tableName != null ? tableName.hashCode() : 0;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + sequence;
        result = 31 * result + (pkTableName != null ? pkTableName.hashCode() : 0);
        result = 31 * result + (pkColumnName != null ? pkColumnName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ForeignKeyInfo{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", columnName='").append(columnName).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", sequence=").append(sequence);
        sb.append(", pkTableName='").append(pkTableName).append('\'');
        sb.append(", pkColumnName='").append(pkColumnName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
