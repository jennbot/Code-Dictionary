package persistence;

import java.io.*;

// A "deleter" that deletes a recipe from termList.txt
public class Deleter {
    private BufferedWriter bufferedWriter;
    private File fileDeleter;

    // EFFECTS: constructs a deleter that will remove recipe from termList.txt
    public Deleter() throws IOException {
        fileDeleter = new File("./data/termList.txt");
        bufferedWriter = new BufferedWriter(new FileWriter(fileDeleter));
    }

    // MODIFIES: this
    // EFFECTS: removes recipe from termList.txt
    public void delete(BufferedReader br, String term) throws IOException {
        String remove = term;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(remove)) {
                currentLine = "";
            }
        }
        close();
    }

    // MODIFIES: this
    // EFFECTS: close bufferedwriter
    public void close() throws IOException {
        bufferedWriter.close();
    }
}
