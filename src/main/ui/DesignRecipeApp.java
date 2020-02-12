package ui;

import model.Favourites;
import model.ListOfRecipe;

import java.util.Scanner;

public class DesignRecipeApp {
    private ListOfRecipe recipelist;
    private Favourites favourites;
    private Scanner input;

    // EFFECT: runs the DesignRecipe app
    public DesignRecipeApp() {
        runDesignRecipe();
    } // Reference 1*

    private void runDesignRecipe() {
        boolean keepGoing = true;
        String command = null;
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

    private void loadRecipes() {              // Reference 2*
        recipelist = new ListOfRecipe();
    }

    private void processCommand(String command) {           // Reference 3*
        if (command.equals("a")) {
            System.out.println(recipelist.allRecipes());
        } else if (command.equals("s")) {
            doSearch();
        } else if (command.equals("add")) {
            addRecipe();
        } else if (command.equals("delete")) {
            deleteRecipe();
        } else {
            System.out.println("Unavailable, please choose again.");
        }
    }

    private void displayMenu() {                                        // Reference 4*
        System.out.println("\nSelect from:");
        System.out.println("\ta -> all recipes");
        System.out.println("\ts -> search");
        System.out.println("\tadd -> add recipe");
        System.out.println("\tdelete -> delete recipe");
        System.out.println("\tfavourite -> add to favourites");
        System.out.println("\tunfavourite -> delete from favourites");
        System.out.println("\tq -> quit");
    }

    private void doSearch() {
        System.out.print("Enter recipe term, ex. 'String', 'ArrayList'");
        String recipe = input.next();

        if (!recipelist.containsRecipeKey(recipe)) {
            System.out.println(recipe + " " + "cannot be found.");
        } else {
            System.out.println(recipelist.searchRecipe(recipe));
        }
    }

    private void addRecipe() {
        System.out.println("Enter new recipe name");
        String term = input.next();

        if (!recipelist.containsRecipeKey(term)) {
            System.out.println("Enter new recipe definition");
            String defn = input.next();

            recipelist.addRecipe(term, defn);
        } else {
            System.out.println(term + " " + "is already added!");
        }
    }

    private void deleteRecipe() {
        System.out.print("Enter recipe");
        String recipe = input.next();

        if (recipelist.containsRecipeKey(recipe)) {
            recipelist.deleteRecipe(recipe);
            System.out.print(recipe + " " + "deleted");
        } else {
            System.out.print(recipe + " " + "cannot be found");
        }
    }
}

// REFERENCES
// All noted references here were constructed using the help of the TellerApp (UI: TellerApp)
// Reference 1: runTeller()
// Reference 2: loadAccounts()
// Reference 3: processCommand()
// Reference 4: displayMenu()
