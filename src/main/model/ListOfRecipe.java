package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ListOfRecipe  {
    private Map<String, Recipe> recipelist;  // a list of recipes


    // EFFECT: base list of definitions in design recipe
    public ListOfRecipe() {
        recipelist = new HashMap<>();
        loadDesignRecipeIntoHM();
    }

    // EFFECT: load recipes into the hashmap (recipelist)
    public void loadDesignRecipeIntoHM() {   //Reference *1
        String fullFilePath = "./data/termList.txt";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fullFilePath));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":::");
                if (parts.length >= 2) {
                    String term = parts[0];
                    String defn = parts[1];
                    recipelist.put(term, new Recipe(defn));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    // REQUIRES: definition must be a non-zero length word
    // EFFECT: If definition is in the design recipe, return the definition. If the definition is not in the design
    //         recipe return "_____ cannot be found".
    public String searchRecipe(String term) {
        String search;
        if (recipelist.containsKey(term)) {
            search = recipelist.get(term).getDefinition();
        } else {
            search = "recipe cannot be found";
        }
        return search;
    }

    // REQUIRES: string is not already in recipelist
    // MODIFIES: this
    // EFFECT: adds new recipe to recipelist
    public void addRecipe(String term, String defn) {
        if (!recipelist.containsKey(term)) {
            recipelist.put(term, new Recipe(defn));
        }
    }

    // REQUIRES: matching recipe in recipelist
    // MODIFIES: this
    // EFFECT: removes recipe from recipelist
    public void deleteRecipe(String term) {
        recipelist.remove(term);
    }


    //EFFECT: produce list of all recipes in DesignRecipe
    public String allRecipes() {
        StringBuilder all = new StringBuilder();
        for (String h : recipelist.keySet()) {
            all.append(h).append("\n");
        }
        return all.toString();
    }

    // EFFECT: produces true if term is a key in recipelist, otherwise false
    public boolean containsRecipeKey(String term) {
        return recipelist.containsKey(term);
    }

    public List<String> getListOfKeys() {
        Set<String> set = recipelist.keySet();
        return new LinkedList<>(set);
    }

    // EFFECT: produce definition of specified recipe
    public String getRecipeDefn(String term) {
        return recipelist.get(term).getDefinition();
    }

    //EFFECT:
    public Set<Map.Entry<String, Recipe>> getEntrySet() {
        return recipelist.entrySet();
    }


}
// REFERENCES
// 1. https://stackoverflow.com/questions/29061782/java-read-txt-file-to-hashmap-split-by