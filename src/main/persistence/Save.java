package persistence;

import model.ListOfRecipe;
import model.Recipe;

import java.io.*;
import java.util.Map;

public class Save extends Persist {
    protected PrintWriter printWriter;

    //EFFECTS: constructs a writer that will write the default data to the termList.txt file
    public Save() throws IOException {
        super("./data/termList.txt", false);
        printWriter = new PrintWriter(super.fileWriter);
    }

    // MODIFIES: this
    // EFFECTS: rewrites file with latest recipelist
    public void write(ListOfRecipe recipelist) {
        for (Map.Entry<String, Recipe> stringRecipeEntry : recipelist.getEntrySet()) {
            String term = String.valueOf(stringRecipeEntry.getKey());
            String defn = recipelist.getRecipeDefn((term));
            printWriter.print(term);
            printWriter.print(":::");
            printWriter.print(defn);
            printWriter.print("\n");
            close();
        }
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing writing data!!
    @Override
    public void close() {
        super.close();
    }
}

