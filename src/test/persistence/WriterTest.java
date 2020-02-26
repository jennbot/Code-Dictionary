package persistence;

import model.ListOfRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriterTest {
    private Writer testwriter;

    @BeforeEach
    void runBefore() throws IOException {
        testwriter = new Writer();
    }

    @Test
    void testWrite() throws IOException {
        // save recipe to testList file
        testwriter.write("hello", "world");
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

