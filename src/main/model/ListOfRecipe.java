package model;

import java.util.HashMap;

//represents the design recipe which has a list of definitions that can be searched up by name and bookmarked
public class ListOfRecipe {
    private HashMap<String, String> recipelist;  // a list of recipes

    /*
     * EFFECT: base list of definitions in design recipe
     */
    public ListOfRecipe() {
        recipelist = new HashMap<>();
        recipelist.put("String", "A variable that contains a collection of characters surrounded by double quotes");
        recipelist.put("Arraylist", "A resizable array.");
        recipelist.put("HashMap", "An array that stores items in key/value pairs");
    }

    /*
     * REQUIRES: definition must be a non-zero length word
     * EFFECT: If definition is in the design recipe, return the definition. If the definition is not in the design
     *         recipe return "_____ cannot be found".
     */
    public String searchRecipe(String term) {
        if (recipelist.containsKey(term)) {
            return recipelist.get(term);
        } else {
            return "recipe cannot be found";
        }
    }

    /* REQUIRES: string is not already in recipelist
     * MODIFIES: this
     * EFFECT: adds new recipe to recipelist
     */
    public void addRecipe(String term, String defn) {
        if (!recipelist.containsKey(term)) {
            recipelist.put(term, defn);
        }
    }

    /* REQUIRES: matching recipe in recipelist
     * MODIFIES: this
     * EFFECT: removes recipe from recipelist
     */

    public void deleteRecipe(String term) {
        if (recipelist.containsKey(term)) {
            recipelist.remove(term);
        }
    }

    /*
     * EFFECT: produce list of all recipes in DesignRecipe
     */
    public String allRecipes() {
        String all = "";
        for (String h : recipelist.keySet()) {
            all += h + "\n";
        }
        return all;
    }

    public boolean containsRecipeKey(String term) {
        return recipelist.containsKey(term);
    }

    /*
     * EFFECT: produce definition of specified recipe
     */
    public String getRecipeDefn(String term) {
        return recipelist.get(term);
    }

}


