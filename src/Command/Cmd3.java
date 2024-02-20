package Command;

import java.io.*;
import java.util.Scanner;

import static Command.Cmd1.splitCsvLine;

public class Cmd3 {
    private static final String CSV_SEPARATOR = ";";
    private static final String CSV_FILE_EXTENSION = ".csv";
    private static final String FILE_SEPARATOR = File.separator;
    private static final String CSV_FILE_PATH_SALES = "src" + FILE_SEPARATOR + "fileCSV" + FILE_SEPARATOR + "vendite" + CSV_FILE_EXTENSION;

    public static void command3() {
        try (Scanner scanner = new Scanner(System.in)) {
            // User input for Sale ID
            int saleId = getValidSaleId(scanner);

            // Check if the sale ID exists in the CSV file
            if (checkSaleIdExistence(saleId)) {
                // Delete the sale and update the Disponibile field
                deleteSaleAndUpdateDisponibile(saleId);
                System.out.println("Capo returned successfully.");
            } else {
                System.out.println("Sale not found with the specified ID.");
            }
        } catch (IOException e) {
            System.err.println("Error during the return of the capo.");
            e.printStackTrace();
        }
    }

    private static int getValidSaleId(Scanner scanner) {
        int saleId;
        do {
            System.out.print("Enter the Sale ID: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. ID must be an integer.");
                scanner.next(); // consume the invalid input
            }
            saleId = scanner.nextInt();
        } while (saleId <= 0);

        return saleId;
    }

    private static boolean checkSaleIdExistence(int saleId) throws IOException {
        try (Scanner csvScanner = new Scanner(new File(CSV_FILE_PATH_SALES))) {
            csvScanner.nextLine(); // Skip header line

            while (csvScanner.hasNextLine()) {
                String[] elements = splitCsvLine(csvScanner.nextLine());

                // Check if Sale ID matches
                if (elements.length >= 1 && Integer.parseInt(elements[0]) == saleId) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void deleteSaleAndUpdateDisponibile(int saleId) throws IOException {
        File originalFile = new File(CSV_FILE_PATH_SALES);
        File tempFile = new File(CSV_FILE_PATH_SALES + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] currentLine = splitCsvLine(line);

                // Ensure the Sale ID is numeric before parsing
                if (currentLine.length >= 1 && isNumeric(currentLine[0])) {
                    int currentSaleId = Integer.parseInt(currentLine[0]);

                    // Check if the current line ID matches the specified Sale ID
                    if (currentSaleId == saleId) {
                        // Update Disponibile field if the array has enough elements
                        if (currentLine.length >= 7) {
                            currentLine[6] = "YES";
                        } else {
                            // Handle the case where the array doesn't have enough elements
                            continue; // Skip this line and move to the next one
                        }
                    }
                }

                // Write the line to the temporary file
                writer.write(String.join(CSV_SEPARATOR, currentLine) + "\n");
            }

        } catch (IOException e) {
            throw e;
        }

        // Delete the original file
        if (!originalFile.delete()) {
            System.err.println("Error deleting the original file.");
            return;
        }

        // Rename the temporary file to replace the original file
        if (!tempFile.renameTo(originalFile)) {
            System.err.println("Error restoring the original file.");
        } else {
            System.out.println("Sale deleted and Disponibile field updated successfully.");
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}