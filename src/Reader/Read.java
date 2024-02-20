package Reader;

import java.io.FileNotFoundException;

public class Read {
    private static Print printer = new Print();
    public static void read() {
        try {
            printer.readAndPrintFile("src/fileCSV/capi.csv");
            printer.readAndPrintFile("src/fileCSV/vendite.csv");
            printer.readAndPrintFile("src/fileCSV/utenti.csv");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading");
            e.printStackTrace();
        }
    }
}