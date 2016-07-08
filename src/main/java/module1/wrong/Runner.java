package module1.wrong;


import module1.wrong.streamOperators.ConsoleReader;
import module1.wrong.streamOperators.FileReader;
import module1.wrong.streamOperators.IStreamOperator;
import module1.wrong.streamOperators.FileWriter;

import java.io.*;
import java.util.Scanner;

/**
 * Created by user on 08.07.2016.
 */
public class Runner {
    public static void main(String[] args) {
        IStreamOperator reader;
        if (args.length > 0) {
            reader = new FileReader(args[0]);
        } else {
            System.out.println("Enter the Account details in the following format, please.");
            System.out.println("Integer n - number of accounts.");
            System.out.println("Then on the one line separated via space or by pressing 'Enter' next numbers: amount value, discount rate value, operation type (1 - Saving, 2 - Check in, other value - Check in too). \nPlease note that you should write a saving amaunt value in case of saving operation type entered");
            reader = new ConsoleReader();
        }
        reader.doStreamOperation().forEach(e -> e.deposite());

        //YAGNI - what if user wants to save results to the file;
        System.out.println("\n\nDo you want to store results in file? (Y/N)");

        try (Scanner scanner = new Scanner(System.in)) {
            String answer = scanner.nextLine().toUpperCase();
            while (!"Y".equals(answer) && !"N".equals(answer)) {
                System.out.println("Wrong input. Try again please. Answer should be 'Y' or 'N'");
                answer = scanner.nextLine().toUpperCase();
            }

            if ("Y".equals(answer)) {
                IStreamOperator streamOperator = new FileWriter(reader.doStreamOperation());
                streamOperator.doStreamOperation();
                //the Idea is: if for reader and writer i use streams, what if I will use one interface for working with streams? nice!
                //problem is that there is can be situation when we can use output stream instead of input stream and in this case will be confused why it using old data.
                //e.g. example below.
                // it's a kind of Liskov substitution principle - bad modelling plus it's better to know what kind of stream operation will be without any checking.
                //anyway there is no any checking.

                //streamOperator = new FileWriter(streamOperator.doStreamOperation());
                // streamOperator.doStreamOperation();

                //as per this functionality is not necessary for this case it will be removed in the second variant.
                //but if it it would be included into requirements then i would make it same way as readers.
            }
        }
    }
}