package persistence;

import java.io.*;

public class Writer extends Persist {
    private PrintWriter printWriter;

    //EFFECTS: constructs a writer that will write data to a file                     Reference *1
    public Writer() throws IOException {
        super("./data/termList.txt", true);
        printWriter = new PrintWriter(super.fileWriter);
    }

    // MODIFIES: this
    // EFFECTS: appends definition to text file                                        Reference *1
    public void write(String term, String defn) {
        printWriter.print(term);
        printWriter.print(":::");
        printWriter.print(defn);
        printWriter.print("\n");
        close();
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing writing data!!
    @Override
    public void close() {
        super.close();

    }

}

// REFERENCES
// 1. https://howtodoinjava.com/java/io/java-append-to-file/