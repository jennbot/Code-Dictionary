package ui;

//import model.Favourites;

import model.ListOfRecipe;
import persistence.Deleter;
import persistence.Writer;

import java.io.*;
import java.util.Scanner;

// DesignRecipe Application
public class DesignRecipeApp {
    private ListOfRecipe recipelist;
    private Scanner input;

    // EFFECT: runs the DesignRecipe app // Reference 1*
    public DesignRecipeApp() throws FileNotFoundException {
        runDesignRecipe();
    }

    //MODIFIES: this
    //EFFECT: runs user input
    private void runDesignRecipe() throws FileNotFoundException {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        loadRecipes();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you!");
    }

    // EFFECT: initializes list of recipes
    private void loadRecipes() {              // Reference 2*
        recipelist = new ListOfRecipe();
    }           // Reference 2*

    // MODIFIES: this
    // EFFECT: processes user command // Reference 3*
    private void processCommand(String command) throws FileNotFoundException {
        switch (command) {
            case "a":
                System.out.println(recipelist.allRecipes());
                break;
            case "s":
                doSearch();
                break;
            case "add":
                addRecipe();
                break;
            case "delete":
                deleteRecipe();
//        } else if (command.equals("fav")) {
//            favouriteRecipe();
//        } else if (command.equals("unfav")) {
//            unfavouriteRecipe();
                break;
            default:
                System.out.println("Unavailable, please choose again.");
                break;
        }
    }

    // EFFECT: displays user menu
    private void displayMenu() {                                               // Reference 4*
        System.out.println("\nSelect from:");
        System.out.println("\ta -> all recipes");
        System.out.println("\ts -> search");
        System.out.println("\tadd -> add recipe");
        System.out.println("\tdelete -> delete recipe");
//        System.out.println("\tfav -> add to favourites");
//        System.out.println("\tunfav -> delete from favourites");
        System.out.println("\tq -> quit");
    }

    // EFFECT: conducts search in recipe list
    private void doSearch() {
        System.out.print("Enter recipe term, ex. 'String', 'Arraylist'");
        String recipe = input.next() + input.nextLine();

        if (!recipelist.containsRecipeKey(recipe)) {
            System.out.println(recipe + " " + "cannot be found.");
        } else {
            System.out.println(recipelist.searchRecipe(recipe));
        }
    }

    // MODIFIES: this
    // EFFECT: adds a recipe to list of recipe
    private void addRecipe() {
        System.out.println("Enter new recipe name");

        String term = input.next() + input.nextLine();

        if (!recipelist.containsRecipeKey(term)) {
            System.out.println("Enter new recipe definition");
            String defn = input.next() + input.nextLine();

            recipelist.addRecipe(term, defn);
            autoSaveRecipe(term, defn);
            System.out.println(term + " " + "added!");
        } else {
            System.out.println(term + " " + "is already added!");
        }
    }

    // MODIFIES: termList.txt
    // EFFECTS: save recipe to termList.txt
    private void autoSaveRecipe(String term, String defn) {
        try {
            Writer writer = new Writer();
            writer.write(term, defn);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, unable to save" + " " + term);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECT: deletes recipe from list of recipe
    private void deleteRecipe() throws FileNotFoundException {
        System.out.print("Enter recipe");
        String recipe = input.next() + input.nextLine();

        if (recipelist.containsRecipeKey(recipe)) {
            recipelist.deleteRecipe(recipe);
           // autoDeleteRecipe(recipe);
            System.out.print(recipe + " " + "deleted");
        } else {
            System.out.print(recipe + " " + "cannot be found");
        }
    }

    // MODIFIES: termList.txt
    // EFFECT: auto deletes recipe from termList.txt
    private void autoDeleteRecipe(String term) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("./data/termList.txt"));
        try {
            Deleter deleter = new Deleter();
            deleter.delete(br, term);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Sorry, cannot delete recipe");
        }
    }

// REFERENCES*
// All noted references here were constructed using the help of the TellerApp (UI: TellerApp)
// Reference 1: runTeller()
// Reference 2: loadAccounts()
// Reference 3: processCommand()
// Reference 4: displayMenu()


// FAVOURITES - TODO uncomment out Favorites-model and Favourites-test and implement here later

    // MODIFIES: this
    // EFFECT: add recipe to favourites
//    private void favouriteRecipe() {
//        System.out.println("Enter recipe");
//        String recipe = input.next();
//
//        if (!favourites.inFavourites(recipe) && recipelist.containsRecipeKey(recipe)) {
//            System.out.println(recipe + " " + "added to favourites!");
//            favourites.addFavourites(recipe);
//        }
//        if ((favourites.inFavourites(recipe)) && (recipelist.containsRecipeKey(recipe))) {
//            System.out.println(recipe + " " + "already added to favourites");
//        } else {
//            System.out.println(recipe + " " + "not found in recipe list");
//        }
//    }

    // MODIFIES: this
    // EFFECT: deletes recipe from favourites list
//    private void unfavouriteRecipe() {
//        System.out.println("Enter recipe");
//        String recipe = input.next();
//
//        if (favourites.inFavourites(recipe)) {
//            System.out.println(recipe + " " + "deleted from favourites");
//        } else {
//            System.out.println(recipe + " " + "not found in favourites");
//        }
//    }
}

