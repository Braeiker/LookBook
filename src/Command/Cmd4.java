package Command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cmd4 {

    private static final String CSV_SEPARATOR = ";";
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String CSV_FILE_PATH_UTENTI = "src" + FILE_SEPARATOR + "fileCSV" + FILE_SEPARATOR + "utenti.csv";

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_SURNAME = 2;
    private static final int COLUMN_DATE_OF_BIRTH = 3;
    private static final int COLUMN_ADDRESS = 4;
    private static final int COLUMN_DOC_ID = 5;

    // Main method for executing command4
    public static void command4() {
        addUserToList();
    }

    // Method to add a new user to the user list and write to CSV
    private static void addUserToList() {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH_UTENTI, true))) {

            System.out.println("Enter user details:");

            // User input for Name
            String name = getNonEmptyInput(scanner, "Name: ");

            // User input for Surname
            String surname = getNonEmptyInput(scanner, "Surname: ");

            // User input for Date of Birth
            String dateOfBirth = getNonEmptyInput(scanner, "Date of Birth: ");

            // User input for Address
            String address = getNonEmptyInput(scanner, "Address: ");

            // User input for Doc ID
            String docId = getValidDocId(scanner);

            // Calculate the next ID for the new user
            int nextId = calculateNextUserId();

            // Write the new user to the CSV file
            writer.write(nextId + CSV_SEPARATOR +
                    name + CSV_SEPARATOR +
                    surname + CSV_SEPARATOR +
                    dateOfBirth + CSV_SEPARATOR +
                    address + CSV_SEPARATOR +
                    docId + "\n");

            System.out.println("User added successfully. New user ID: " + nextId);
        } catch (IOException e) {
            System.err.println("Error while adding a new user to the CSV file");
            e.printStackTrace();
        }
    }

    // Method to get non-empty input from the user
    private static String getNonEmptyInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    // Method to get valid Doc ID from the user
    private static String getValidDocId(Scanner scanner) {
        String docId;
        while (true) {
            try {
                System.out.print("Doc ID (format: LL NNNNNN N): ");
                docId = scanner.nextLine();
                if (isValidDocId(docId)) {
                    break; // Exit the loop if the input is valid
                } else {
                    System.out.println("Invalid Doc ID format. Please follow the format: LL NNNNNN N");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid Doc ID.");
            }
        }
        return docId;
    }

    // Method to validate the Doc ID format
    private static boolean isValidDocId(String docId) {
        // Expected format: LL NNNNNN N
        return docId.matches("[A-Z]{2} \\d{6} \\d");
    }

    // Method to calculate the next user ID based on existing IDs in the CSV file
    private static int calculateNextUserId() {
        try (Scanner csvScanner = new Scanner(new File(CSV_FILE_PATH_UTENTI))) {
            // Check if there is a next line before attempting to read it
            if (csvScanner.hasNextLine()) {
                csvScanner.nextLine(); // Skip header line

                int maxId = 0;
                while (csvScanner.hasNextLine()) {
                    String[] elements = splitCsvLine(csvScanner.nextLine());
                    int currentId = Integer.parseInt(elements[COLUMN_ID]);
                    maxId = Math.max(maxId, currentId);
                }

                return maxId + 1;
            } else {
                System.err.println("Error: 'utenti.csv' file is empty");
                return 1; // Return a default value or handle the case as needed
            }
        } catch (IOException e) {
            System.err.println("Error while calculating the next user ID");
            e.printStackTrace();
            return -1;
        }
    }

    // Method to split CSV line into elements
    private static String[] splitCsvLine(String line) {
        return line.split(CSV_SEPARATOR, -1);
    }
}
