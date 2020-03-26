package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Persist {
    protected FileWriter fileWriter;
    protected PrintWriter printWriter;

    public Persist(String file, boolean append) throws IOException {
        fileWriter = new FileWriter(new File(file), append);
        printWriter = new PrintWriter(fileWriter);
    }

    public void close() {
        printWriter.close();
    }


}
