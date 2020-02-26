package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.OverWriter;
import persistence.Writer;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ListOfRecipeTest {
    ListOfRecipe testlist;

    @BeforeEach
    void runBefore() {
        testlist = new ListOfRecipe();
    }

    @Test
    void testConstructor() {
        testlist.loadDesignRecipeIntoHM();
        assertEquals(testlist.allRecipes(), "HashMap" + "\n" + "Arraylist" + "\n" + "String"+"\n");
    }

    @Test
    void testIOException() {
        String fullFilePath = "./data/emptyList.txt";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fullFilePath));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":::");
                if (parts.length >= 2) {
                    String term = parts[0];
                    String defn = parts[1];
                    fail();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void testSearchWord() {
        assertEquals(testlist.searchRecipe("String"),
                "A variable that contains a collection of characters surrounded by double quotes " +
                        "---> \"string\", \"hello\", \"world\"");
        assertEquals(testlist.searchRecipe("hello"), "recipe cannot be found");
    }

    @Test
    void testAddRecipe() {
        testlist.addRecipe("hello", "world");
        assertEquals(testlist.searchRecipe("hello"), "world");
        assertEquals(testlist.allRecipes(), "HashMap\nArraylist\nString\nhello\n");

        testlist.addRecipe("String", "haha");
        assertEquals(testlist.searchRecipe("String"),
                "A variable that contains a collection of characters surrounded by double quotes " +
                        "---> \"string\", \"hello\", \"world\"");
        assertEquals(testlist.allRecipes(), "HashMap\nArraylist\nString\nhello\n");

    }

    @Test
    void testDeleteRecipe() {
        testlist.deleteRecipe("String");
        assertEquals(testlist.searchRecipe("String"), "recipe cannot be found");
        assertEquals(testlist.allRecipes(), "HashMap\nArraylist\n");

        testlist.deleteRecipe("hello");
        assertEquals(testlist.searchRecipe("hello "), "recipe cannot be found");
        assertEquals(testlist.allRecipes(), "HashMap\nArraylist\n");
    }

    @Test
    void testAllRecipe() {
        assertEquals(testlist.allRecipes(), "HashMap\nArraylist\nString\n");
    }

    @Test
    void testContainsRecipeKey() {
        assertTrue(testlist.containsRecipeKey("String"));
        assertFalse(testlist.containsRecipeKey("mantou"));
    }

    @Test
    void testGetRecipeDefn() {
        assertEquals(testlist.getRecipeDefn("String"),
                "A variable that contains a collection of characters surrounded by double quotes " +
                        "---> \"string\", \"hello\", \"world\"");
    }
}