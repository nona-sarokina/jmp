package module1.notSoWrong;


import module1.notSoWrong.readers.ConsoleAccountReader;
import module1.notSoWrong.readers.FileAccountReader;
import module1.notSoWrong.readers.IAccountReader;

import java.io.FileNotFoundException;

/**
 * Created by user on 08.07.2016.
 */
public class Runner {
    public static void main(String[] args) throws FileNotFoundException {

        IAccountReader reader;
        if (args.length > 0) {
            //input values order:
            //amount, discaunt rate, type, savingAmount if type is 1 (SAVING)
            reader = new FileAccountReader(args[0]);
        } else {
            // 3
            // 10000 0,1 1 1000
            // 100000 0,2 2
            // 100000 0,15 3
            System.out.println("Enter the Account details in the following format, please.");
            System.out.println("Integer n - number of accounts.");
            System.out.println("Then on the one line separated via space or by pressing 'Enter' next numbers: amount value, discount rate value, operation type (1 - Saving, 2 - Check in, other value - Check in too). \nPlease note that you should write a saving amaunt value in case of saving operation type entered");
            reader = new ConsoleAccountReader();
        }

        reader.read().forEach(e -> e.deposite());
    }
}
