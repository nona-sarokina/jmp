package module10.task1.beans;

/**
 * Created by user on 22.09.2016.
 */
public class ColumnData {
    public static final String AUTO_INCREMENT = "AUTO_INCREMENT";
    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String NOT_NULL = "NOT NULL";
    private String type;
    private int length;
    private String name;

    private boolean isAutoincrement;
    private boolean isNotNull;
    private boolean isPrimaryKey;

    public enum ColumnNames {
        IS_NULLABLE,
        TABLE_NAME,
        TABLE_SCHEMA,
        COLUMN_NAME,
        TYPE_NAME,
        IS_AUTOINCREMENT
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAutoincrement() {
        return isAutoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        isAutoincrement = autoincrement;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public void setNotNull(boolean notNull) {
        isNotNull = notNull;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getColumnCreationString() {
        return  getName() + " " + getType()  + " " + getLength();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnData that = (ColumnData) o;

        if (length != that.length) return false;
        if (isAutoincrement != that.isAutoincrement) return false;
        if (isNotNull != that.isNotNull) return false;
        if (isPrimaryKey != that.isPrimaryKey) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + length;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isAutoincrement ? 1 : 0);
        result = 31 * result + (isNotNull ? 1 : 0);
        result = 31 * result + (isPrimaryKey ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
       /* final StringBuilder sb = new StringBuilder("ColumnData{");
        sb.append("type='").append(type).append('\'');
        sb.append(", length=").append(length);
        sb.append(", name='").append(name).append('\'');
        sb.append(", isAutoincrement=").append(isAutoincrement);
        sb.append(", isNotNull=").append(isNotNull);
        sb.append(", isPrimaryKey=").append(isPrimaryKey);
        sb.append('}');
        return sb.toString();*/
        return  name;
    }

    public String getOptions() {
        StringBuilder options = new StringBuilder(name);
        options.append(" ").append(type);

        if (isAutoincrement()) {
            options.append(" ").append(AUTO_INCREMENT);
        }
        if (isPrimaryKey()) {
            options.append(" ").append(PRIMARY_KEY);
        }
        if (isNotNull()) {
            options.append(" ").append(NOT_NULL);
        }
        return options.toString();
    }
}
