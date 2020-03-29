package ui.gui.console;

import model.ListOfRecipe;

import persistence.OverWriter;
import persistence.Save;
import persistence.Writer;

import java.io.*;
import java.util.Scanner;

// DesignRecipe Application
public class DesignRecipeApp {
    private ListOfRecipe recipelist;
    private Scanner input;

    // EFFECT: runs the DesignRecipe app
    public DesignRecipeApp() throws IOException {
        // Reference 1*
    }

    //MODIFIES: this
    //EFFECT: runs user input
    public void runDesignRecipe() throws IOException {
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
    public void loadRecipes() {  // Reference 2*
        recipelist = new ListOfRecipe();
        recipelist.loadDesignRecipeIntoHM();
    }

    // MODIFIES: this
    // EFFECT: processes user command // Reference 3*
    public void processCommand(String command) throws IOException {
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
                break;
            case "reset":
                resetRecipeDefault();
                break;
            case "save":
                saveRecipe();
                break;
            default:
                System.out.println("Unavailable, please choose again.");
                break;
        }
    }

    // EFFECT: displays user menu
    public void displayMenu() {  // Reference 4*
        System.out.println("\nSelect from:");
        System.out.println("\ta -> all recipes");
        System.out.println("\ts -> search");
        System.out.println("\tadd -> add recipe");
        System.out.println("\tdelete -> delete recipe");
        System.out.println("\tsave -> save recipe");
        System.out.println("\treset -> restore DesignRecipe to default recipes list");
        System.out.println("\tq -> quit");
    }

    // EFFECT: conducts search in recipe list
    public void doSearch() {
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
    public void addRecipe() throws IOException {
        System.out.println("Enter new recipe name");

        String term = input.next() + input.nextLine();

        if (!recipelist.containsRecipeKey(term)) {
            System.out.println("Enter new recipe definition");
            String defn = input.next() + input.nextLine();
            recipelist.addRecipe(term, defn);
        }
    }

    public void saveRecipe() throws IOException {
        Save saver = new Save();
        saver.write(recipelist);
    }

    // MODIFIES: this
    // EFFECT: deletes recipe from list of recipe
    public void deleteRecipe() {
        System.out.print("Enter recipe");
        String recipe = input.next() + input.nextLine();

        if (recipelist.containsRecipeKey(recipe)) {
            recipelist.deleteRecipe(recipe);
            System.out.print(recipe + " " + "deleted");
        } else {
            System.out.print(recipe + " " + "cannot be found");
        }
    }

    // MODIFIES: this
    // EFFECT: loads defaultList data into termList
    public void resetRecipeDefault() throws IOException {
        OverWriter overwriter = new OverWriter();
        overwriter.overWrite();
        overwriter.close();
        loadRecipes();
    }
}

// REFERENCES*
// All noted references here were constructed using the help of the TellerApp (UI: TellerApp)
// Reference 1: runTeller()
// Reference 2: loadAccounts()
// Reference 3: processCommand()
// Reference 4: displayMenu()

