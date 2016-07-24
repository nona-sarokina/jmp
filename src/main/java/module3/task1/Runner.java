package module3.task1;

import module3.task1.beans.Mode;
import module3.task1.beans.Person;
import module3.task1.factory.AbstractReadWriteFactory;
import module3.task1.factory.DBReadWriteFactory;
import module3.task1.factory.FileReadWriteFactory;
import module3.task1.io.readers.IPersonReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by user on 23.07.2016.
 */
/*
Person
Write a program that supports writing and reading from files and DB (Access DB using JDBC.ODBC)

Writing to a file includes these features: Defining the file name to write or read from Wrapping with
a buffer Writing/Reading Persons

Writing to the DB is also done in three steps: Loading driver and creating connection Person
to DB serializer which breaks Objects into Record and vise versa

Writing/Reading Persons Client chooses to work with files or DB but once the choice was made –
client code is identical in both cases.

This means that and beside specifying the source (File/DB) working with the actual resource
should be transparent and includes the following operations:
void writePerson (Person)
Person readPerson()
Person readPerson (String name)
*/
public class Runner {
    public static void main(String[] args) {

        Mode mode = null;
        while (true) {
            System.out.println("Please enter mode. Possible values are DB and FILE");
            Scanner s = new Scanner(System.in);
            try {
                mode = Mode.valueOf(s.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
            }
            if (Mode.DB.equals(mode) || Mode.FILE.equals(mode)) {
                break;
            }
            System.out.println("Error :(");
        }

        AbstractReadWriteFactory factory = null;
        switch (mode) {
            case DB:
                factory = new DBReadWriteFactory();
                break;
            case FILE:
                factory = new FileReadWriteFactory();
                break;
        }

        IPersonReader reader = factory.getReader();
        System.out.println("All persons are:");
        System.out.println(reader.readPersons());
        System.out.println("Isaak Cleve data is:");
        System.out.println(reader.readPerson("Isaak Cleve"));
        System.out.println("Inserting Craig Neu");
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        String paramDateAsString = "1978-11-30";
        try {
            Date date = textFormat.parse(paramDateAsString);
            factory.getWriter().writePerson(new Person("Craig Neu", date, "1329 American Drive", "Tallahassee", 32308));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Craig Neu data is:");
        System.out.println(reader.readPerson("Craig Neu"));
        System.out.println("All persons are:");
        System.out.println(reader.readPersons());

    }
}
