package persistence;

import model.ListOfRecipe;
import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveTest {
    private Save testwriter;
    private ListOfRecipe testrecipe;

    @BeforeEach
    void runBefore() throws IOException {
        testwriter = new Save();
        testrecipe = new ListOfRecipe();
        testrecipe.addRecipe("hello", "world");
    }

    @Test
    void testWrite() throws IOException {
        // save recipe to testList file
        testwriter.write(testrecipe);
        testwriter.close();

        // verify save
        assertEquals(testrecipe.getRecipeDefn("hello"), "world");
        assertTrue(testrecipe.containsRecipeKey("hello"));

        //resets everything back
        OverWriter testow = new OverWriter();
        testow.overWrite();
        testow.close();
    }

}
