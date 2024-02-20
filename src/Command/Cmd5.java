package Command;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cmd5 {

    private static final String CSV_SEPARATOR = ";";
    private static final String CSV_FILE_EXTENSION = ".csv";
    private static final String FILE_SEPARATOR = File.separator;
    private static final String CSV_FILE_DIRECTORY = "src" + FILE_SEPARATOR + "fileCSV" + FILE_SEPARATOR;
    private static final String EXPORT_DIRECTORY = "src" + FILE_SEPARATOR + "FileExportCapiAvailable" + FILE_SEPARATOR;

    // Paths to the CSV files
    private static final String CSV_FILE_PATH_AVAILABLE = CSV_FILE_DIRECTORY + "formatCapi" + CSV_FILE_EXTENSION;

    // Main method for executing command5
    public static void command5() {
        transformAndExportItems();
    }

    // Method to transform and export available items to CSV file
    private static void transformAndExportItems() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH_AVAILABLE))) {

            // Get current date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
            String currentDate = dateFormat.format(new Date());

            // Create the export directory if it doesn't exist
            File exportDirectory = new File(EXPORT_DIRECTORY);
            if (!exportDirectory.exists()) {
                exportDirectory.mkdirs();
            }

            // Create the transformed CSV file with current date in the name in the export directory
            String transformedFilePath = EXPORT_DIRECTORY + "capi_" + currentDate + CSV_FILE_EXTENSION;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(transformedFilePath))) {

                // Write header line to the new CSV file
                writer.write(reader.readLine() + "\n");

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] elements = splitCsvLine(line);

                    // Check if the last element is "SI" (available)
                    if (elements.length >= 7 && "SI".equalsIgnoreCase(elements[6].trim())) {

                        // Transform logic: Example - Convert the type to uppercase
                        elements[2] = elements[2].toUpperCase();

                        // Write the transformed item to the new CSV file
                        writer.write(String.join(CSV_SEPARATOR, elements) + "\n");
                    }
                }

                System.out.println("Transformation and export completed successfully. File: " + transformedFilePath);
            } catch (IOException e) {
                System.err.println("Error during transformation and export");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("Error opening availableCapi file");
            e.printStackTrace();
        }
    }

    // Method to split CSV line into elements
    private static String[] splitCsvLine(String line) {
        return line.split(CSV_SEPARATOR, -1);
    }
}
