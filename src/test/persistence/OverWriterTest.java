package persistence;

import model.ListOfRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverWriterTest {
    private PrintWriter testWriter;
    private FileWriter testFileWriter;
    private String testfile = "./data/termList.txt";

    @BeforeEach
    void runBefore() throws IOException {
        testFileWriter = new FileWriter(testfile, false);
        testWriter = new PrintWriter(testFileWriter);
    }

    @Test
    void testOverWrite() throws IOException {
        // produce empty list
        testWriter.write("");
        ListOfRecipe emptylist = new ListOfRecipe();
        assertEquals(emptylist.allRecipes(), "");

        // reload default list
        OverWriter testow = new OverWriter();
        testow.overWrite();

        // verify default list has loaded
        ListOfRecipe defaultlist = new ListOfRecipe();
        assertEquals(defaultlist.allRecipes(),"HashMap\nArraylist\nString\n");

    }
}

