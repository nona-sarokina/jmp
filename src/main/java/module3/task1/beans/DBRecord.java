package module3.task1.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by user on 24.07.2016.
 */
public class DBRecord extends Person {

    public DBRecord(String name, Date dateOfBirth, String address, String city, int zipcode) {
        super(name, dateOfBirth, address, city, zipcode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormatter.format(this.getDateOfBirth());
        formatter.format("('%s','%s','%s','%s', %d)", this.getName(), formattedDate, this.getAddress(),this.getCity(), this.getZipCode());
        return sb.toString();
    }
}
