package model;

import java.util.HashMap;

public class Favourites {
    private HashMap<String, String> favourites;// a list of favourite recipes
    private ListOfRecipe recipelist = new ListOfRecipe();

    // EFFECT: construct empty favourites list
    public Favourites() {
        favourites = new HashMap<>();
    }

    /*
     * REQUIRES: definition must be in list of recipe.
     * EFFECT: if definition is not already in list, add's definition into favourites list
     *         if definition is already in list, state "recipe already added"
     */
    public void addFavourites(String term) {
        if (!favourites.containsKey(term) && recipelist.containsRecipeKey(term)) {
            favourites.put(term, recipelist.getRecipeDefn(term));
        }
    }

    /*
     * EFFECT: if definition is in the list, remove from list
     *         if definition is not in the list, state "recipe not found"
     */
    public void deleteFavourites(String term) {
        if (favourites.containsKey(term)) {
            favourites.remove(term);
        }
    }

    /*
     * EFFECT: produce list of all favourites
     */
    public String favourites() {
        String all = "";
        for (String h : favourites.keySet()) {
            all += h + "\n";
        }
        return all;
    }

    /*
     * EFFECT: produce true to if term is in favourites, otherwise produce false
     */

    public boolean inFavourites(String term) {
        return favourites.containsKey(term);
    }
}
