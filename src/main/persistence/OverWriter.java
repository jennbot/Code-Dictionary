package persistence;

import java.io.*;
import java.util.Scanner;


public class OverWriter {
    private PrintWriter printWriter;
    private FileWriter fileWriter;

    //EFFECTS: constructs a writer that will write data to a file
    public OverWriter() throws IOException {
        fileWriter = new FileWriter("./data/termList.txt");
        printWriter = new PrintWriter(fileWriter);
    }

    // MODIFIES: this
    // EFFECTS: overwrites termList.txt with defaultList.txt
    public void overWrite() throws FileNotFoundException {
        Scanner inFile = new Scanner(new File("./data/defaultList.txt"));
        while (inFile.hasNext()) {
            printWriter.print(inFile.nextLine());
            printWriter.print("\n");
        }
        printWriter.close();
        inFile.close();
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing writing data!!
    public void close() {
        printWriter.close();
    }
}
