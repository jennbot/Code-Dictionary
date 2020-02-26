package persistence;

import model.ListOfRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriterTest {
    private PrintWriter testWriter;
    private FileWriter testFileWriter;
    private String testlist = "./data/termList.txt";

    @BeforeEach
    void runBefore() throws IOException {
        testFileWriter = new FileWriter(testlist, true);
        testWriter = new PrintWriter(testFileWriter);
    }

    @Test
    void testWrite() throws IOException {
        // save recipe to testList file
        Writer testwriter = new Writer();
        testwriter.write("hello", "world");

        // verify "hello" has been added to testList.txt
        ListOfRecipe checklist = new ListOfRecipe();
        assertEquals(checklist.getRecipeDefn("hello"), "world" );
        assertTrue(checklist.containsRecipeKey("hello"));
//        Scanner check = new Scanner(new File("./data/testList.txt"));
//        while (check.hasNext()) {
//            String line = check.nextLine();
//            assertEquals(line, "hello" + ":::" + "world");

        }
}

