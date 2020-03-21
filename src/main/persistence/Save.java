package persistence;

import model.ListOfRecipe;
import model.Recipe;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Save {

    private PrintWriter printWriter;
    private FileWriter fileWriter;

    //EFFECTS: constructs a writer that will write the default data to the termList.txt file
    public Save() throws IOException {
        fileWriter = new FileWriter("./data/termList.txt", false);
        printWriter = new PrintWriter(fileWriter);
    }

    // MODIFIES: this
    // EFFECTS: rewrites file with latest recipelist
    public void write(ListOfRecipe recipelist) {
        for (Map.Entry<String, Recipe> stringRecipeEntry : recipelist.getEntrySet()) {
            Map.Entry me = stringRecipeEntry;
            String term = String.valueOf(me.getKey());
            String defn = recipelist.getRecipeDefn((term));
            printWriter.print(term);
            printWriter.print(":::");
            printWriter.print(defn);
            printWriter.print("\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing writing data!!
    public void close() {
        printWriter.close();
    }
}

