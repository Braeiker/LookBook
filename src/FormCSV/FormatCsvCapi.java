package FormCSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FormatCsvCapi {
    public static void formatCapi() { // Added parentheses here
        try {
            File inputFile = new File("src/fileCSV/capi.csv");
            Scanner scanner = new Scanner(inputFile);
            File outputFile = new File("src/fileCSV/formatCapi.csv");
            FileWriter writer = new FileWriter(outputFile);

            // Leggi e scrivi l'intestazione
            String header = scanner.nextLine();
            writer.write(header + "\n");

            // Leggi e scrivi ogni riga con le colonne desiderate
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(";");
                if (columns.length >= 7) {  // Verifica che ci siano almeno 7 colonne
                    writer.write(columns[0] + ";" + columns[1] + ";" + columns[2]
                            + ";" + columns[3] + ";" + columns[4] + ";" + columns[5] + ";" + columns[6] + ";\n");
                }
            }

            // Chiudi gli stream
            scanner.close();
            writer.close();

            System.out.println("Nuovo file creato con successo!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}