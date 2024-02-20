import Command.CmdApplication;
import FormCSV.FormatCsvCapi;
import Reader.Read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Format the file corsi
        File formattedFile = new File("src/fileCSV/formatCapi.csv");

        if (formattedFile.exists()) {
            System.out.println(" ");

        } else {
            Scanner inputScanner = new Scanner(System.in);

            System.out.println("Do you want to continue formatting the file?(YES/PRESS ANY KEY)");
            String userResponse = inputScanner.nextLine().toUpperCase();

            if (userResponse.equals("YES")) {
                FormatCsvCapi.formatCapi();
            } else if (userResponse.equals("NO")) {
                System.out.println("Operation canceled by user. Continue to the next function if necessary.");
                // Call next function
            } else {
                System.out.println("Invalid input.");
            }

            inputScanner.close();


        }
        // If the user wants to read all leaders, users, sales
        System.out.println("Do you want to read the contents of the files present in the system? (YES/PRESS ANY KEY)");
        Scanner scanner = new Scanner(System.in);

        String choice = scanner.nextLine().toUpperCase();

        if (choice.equals("YES")) {
            Read.read();
            System.out.println("Do you want to continue with the command list? (YES/PRESS ANY KEY)");
            String continueChoice = scanner.nextLine().toUpperCase();
            if (continueChoice.equals("YES")) {
                CmdApplication.cmdList();
            } else {
                System.out.println("Exiting program. Goodbye!");
            }


        } else {
            // COMMAND LIST
            CmdApplication.cmdList();
        }

        scanner.close();
    }
}