package persistence;

import java.io.*;
import java.util.Scanner;


//public class OverWriter {
public class OverWriter extends Persist {
    protected PrintWriter printWriter;

    //EFFECTS: constructs a writer that will write the default data to the termList.txt file
    public OverWriter() throws IOException {
        super("./data/termList.txt", false);
        printWriter = new PrintWriter(super.fileWriter);
    }

    // MODIFIES: this
    // EFFECTS: overwrites termList.txt with defaultList.txt
    public void overWrite() throws FileNotFoundException {
        Scanner inFile = new Scanner(new File("./data/defaultList.txt"));
        while (inFile.hasNext()) {
            printWriter.print(inFile.nextLine());
            printWriter.print("\n");
        }
        close();
        inFile.close();
        System.out.println("Default recipes restored!");
    }


    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing writing data!!
    @Override
    public void close() {
        super.close();
    }
}
