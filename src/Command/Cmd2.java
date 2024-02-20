package Command;

import java.io.*;
import java.util.Scanner;

public class Cmd2 {

    private static final String CSV_SEPARATOR = ";";
    private static final String CSV_FILE_EXTENSION = ".csv";
    private static final String FILE_SEPARATOR = File.separator;

    // Paths to the CSV files
    private static final String CSV_FILE_PATH = "src" + FILE_SEPARATOR + "fileCSV" + FILE_SEPARATOR + "formatCapi" + CSV_FILE_EXTENSION;
    private static final String CSV_FILE_PATH_SALES = "src" + FILE_SEPARATOR + "fileCSV" + FILE_SEPARATOR + "vendite" + CSV_FILE_EXTENSION;

    private static final int COLUMN_CAPO_ID = 1;
    private static final int COLUMN_USER_ID = 2;

    // Main method for executing command2
    public static void command2() {
        addNewRowToCsv();
    }

    // Method to add a new row to the CSV file
    private static void addNewRowToCsv() {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH_SALES, true))) {

            // User input for Capo ID
            String capoId = getNonEmptyInput(scanner, "Enter the Capo ID: ");

            // User input for User ID
            String userId = getNonEmptyInput(scanner, "Enter the User ID: ");

            // Calculate the next ID for the new row
            int nextId = calculateNextId();

            // Write the new row to the CSV file
            writer.write(nextId + CSV_SEPARATOR + capoId + CSV_SEPARATOR + userId + "\n");




            System.out.println( "New sales added successfully.");
        } catch (IOException e) {
            System.err.println("Error while adding a new row to the CSV file");
            e.printStackTrace();
        }
    }

    // Method to get non-empty user input
    private static String getNonEmptyInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    // Method to process the CSV file based on Capo ID and User ID
    private static void processCsvFile(String capoId, String userId) {
        try (Scanner csvScanner = new Scanner(new File(CSV_FILE_PATH))) {
            csvScanner.nextLine(); // Skip header line

            while (csvScanner.hasNextLine()) {
                String[] elements = splitCsvLine(csvScanner.nextLine());

                // Check if Capo ID and User ID match
                if (elements.length >= 3 && elements[COLUMN_CAPO_ID].equals(capoId) && elements[COLUMN_USER_ID].equals(userId)) {
                    // Update CSV file if a specific condition is met
                    if ("NO".equalsIgnoreCase(elements[6].trim())) {
                        elements[6] = "YES";
                        updateCsvFile(elements);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: CSV file not found");
            e.printStackTrace();
        }
    }


    // Method to update the CSV file
    private static void updateCsvFile(String[] elements) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH + ".tmp"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] currentLine = splitCsvLine(line);

                // Check if the current line ID matches the updated elements ID
                if (currentLine.length >= 1 && currentLine[0].equals(elements[0])) {
                    line = String.join(CSV_SEPARATOR, elements);
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error while updating the CSV file");
            e.printStackTrace();
        }
    }

    // Method to split CSV line into elements
    private static String[] splitCsvLine(String line) {
        return line.split(CSV_SEPARATOR, -1);
    }

    // Method to calculate the next ID based on existing IDs in the CSV file
    private static int calculateNextId() {
        File salesFile = new File(CSV_FILE_PATH_SALES);

        // Check if the "vendite.csv" file exists
        if (!salesFile.exists()) {
            System.err.println("Error: 'vendite.csv' file not found");
            return -1; // Handle the case as needed
        }

        try (Scanner csvScanner = new Scanner(salesFile)) {
            // Check if there is a next line before attempting to read it
            if (csvScanner.hasNextLine()) {
                csvScanner.nextLine(); // Skip header line

                int maxId = 0;
                while (csvScanner.hasNextLine()) {
                    String[] elements = splitCsvLine(csvScanner.nextLine());
                    int currentId = Integer.parseInt(elements[0]);
                    maxId = Math.max(maxId, currentId);
                }

                return maxId + 1;
            } else {
                System.err.println("Error: 'vendite.csv' file is empty");
                return 1; // Return a default value or handle the case as needed
            }
        } catch (IOException e) {
            System.err.println("Error while calculating the next ID");
            e.printStackTrace();
            return -1;
        }
    }
}
