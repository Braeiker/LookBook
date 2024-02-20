package Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cmd1 {

    public static Object Command1;

    // Method to initiate the CSV conversion
    public static void command1() {
        String filePath = "src/fileCSV/capi.csv";
        convertFileCSV(filePath);
    }

    // Method to read and process the CSV file
    private static void convertFileCSV(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty() && !line.startsWith(";")) {
                    String[] fields = splitCsvLine(checkAndCombineLines(scanner, line));
                    printInfo(fields);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Check and combine lines of the CSV file if they do not end with a semicolon
    static String checkAndCombineLines(Scanner scan, String currentLine) {
        if (!currentLine.endsWith(";") && scan.hasNext()) {
            currentLine += scan.nextLine();
        }
        return currentLine;
    }

    // Split the CSV line into elements
    static String[] splitCsvLine(String line) {
        return line.trim().split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    // Method to print information about each item
    private static void printInfo(String[] fields) {
        if (fields.length >= 7) {
            try {
                int id = Integer.parseInt(fields[0]);
                String insertionDate = fields[1];
                String type = fields[2];
                String brand = fields[3];
                String size = fields[4];
                String price = fields[5];
                String available = fields[6];

                // Print item information
                System.out.println("ID: " + id);
                System.out.println("Insertion Date: " + insertionDate);
                System.out.println("Type: " + type);
                System.out.println("Brand: " + brand);
                System.out.println("Size: " + size);
                System.out.println("Price: " + price);
                System.out.println("Available: " + available);
                System.out.println("------------------------------");
            } catch (NumberFormatException e) {
                // Print the content of the ID field when a NumberFormatException occurs
                System.out.println("Error: Invalid ID format - " + fields[0]);
            }
        } else {
            // Handle the case where the number of fields is invalid
            System.out.println("Error: Invalid number of fields for the line.");
        }
    }
}
