package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ListOfRecipeTest {
    ListOfRecipe testlist;

    @BeforeEach
    void runBefore() {
        testlist = new ListOfRecipe();
    }

    @Test
    void testSearchWord() {
        assertEquals(testlist.searchRecipe("String"), "A variable that contains a collection of characters surrounded by double quotes");
        assertEquals(testlist.searchRecipe("hello"), "recipe cannot be found");
    }

    @Test
    void testAddRecipe() {
        testlist.addRecipe("hello", "world");
        assertEquals(testlist.searchRecipe("hello"), "world");
        assertEquals(testlist.allRecipes(), "Arraylist\nString\nhello\n");

        testlist.addRecipe("String", "haha");
        assertEquals(testlist.searchRecipe("String"), "A variable that contains a collection of characters surrounded by double quotes");
        assertEquals(testlist.allRecipes(), "Arraylist\nString\nhello\n");

    }

    @Test
    void testDeleteRecipe() {
        testlist.deleteRecipe("String");
        assertEquals(testlist.searchRecipe("String"), "recipe cannot be found");
        assertEquals(testlist.allRecipes(), "Arraylist\n");

        testlist.deleteRecipe("hello");
        assertEquals(testlist.searchRecipe("hello "), "recipe cannot be found");
        assertEquals(testlist.allRecipes(), "Arraylist\n");
    }

}