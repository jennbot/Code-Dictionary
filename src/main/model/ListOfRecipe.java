package model;

import java.util.HashMap;

public class ListOfRecipe {
    private HashMap<String, String> recipelist;  // a list of recipes


    // EFFECT: base list of definitions in design recipe
    public ListOfRecipe() {
        recipelist = new HashMap<>();
        recipelist.put("String", "A variable that contains a collection of characters surrounded by double quotes");
        recipelist.put("Arraylist", "A resizable array.");
        recipelist.put("HashMap", "An array that stores items in key/value pairs");
    }

    // REQUIRES: definition must be a non-zero length word
    // EFFECT: If definition is in the design recipe, return the definition. If the definition is not in the design
    //         recipe return "_____ cannot be found".
    public String searchRecipe(String term) {
        return recipelist.getOrDefault(term, "recipe cannot be found");
    }

    // REQUIRES: string is not already in recipelist
    // MODIFIES: this
    // EFFECT: adds new recipe to recipelist
    public void addRecipe(String term, String defn) {
        if (!recipelist.containsKey(term)) {
            recipelist.put(term, defn);
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

    // EFFECT: produce definition of specified recipe
    public String getRecipeDefn(String term) {
        return recipelist.get(term);
    }

}


