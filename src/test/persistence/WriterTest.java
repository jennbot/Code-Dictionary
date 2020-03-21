package persistence;

import model.ListOfRecipe;
import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriterTest {
    private Writer testwriter;
    private Recipe testrecipe;

    @BeforeEach
    void runBefore() throws IOException {
        testwriter = new Writer();
//        testrecipe = new Recipe("world");
    }

    @Test
    void testWrite() throws IOException {
        // save recipe to testList file
        testwriter.write("hello", "world");
//        testwriter.write("hello:::world");
        testwriter.close();

        // verify "hello" has been added to testList.txt
        ListOfRecipe checklist = new ListOfRecipe();
        assertEquals(checklist.getRecipeDefn("hello"), "world");
        assertTrue(checklist.containsRecipeKey("hello"));

        // reset termlist to default
        OverWriter reset = new OverWriter();
        reset.overWrite();
    }

}

