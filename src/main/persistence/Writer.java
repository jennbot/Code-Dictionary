package persistence;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

// A writer that can write a definition to a file
public class Writer {
    private PrintWriter printWriter;
    private FileWriter fileWriter;

    //EFFECTS: constructs a writer that will write data to a file                     Reference *1
    public Writer() throws IOException {
        fileWriter = new FileWriter("./data/termList.txt", true);
        printWriter = new PrintWriter(fileWriter);
    }

    // MODIFIES: this
    // EFFECTS: appends definition to text file                                        Reference *1
    public void write(String term, String defn) {
        printWriter.print(term);
        printWriter.print(":::");
        printWriter.print(defn);
        printWriter.print("\n");
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing writing data!!
    public void close() {
        printWriter.close();
    }

}

// REFERENCES
// 1. https://howtodoinjava.com/java/io/java-append-to-file/