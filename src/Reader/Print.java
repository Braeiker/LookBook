package Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Print {

    public void readAndPrintFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                printData(data);
            }
        }
    }

    private void printData(String data) {
        String[] dataArray = data.split(";");
        for (String val : dataArray) {
            if (!val.isEmpty()) {
                System.out.println(val);
            }
        }
    }
}
