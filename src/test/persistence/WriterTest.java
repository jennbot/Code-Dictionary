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
    private String testlist = "./data/testList";

    @BeforeEach
    void runBefore() throws IOException {
        testFileWriter = new FileWriter(testlist, true);
        testWriter = new PrintWriter(testFileWriter);
    }

    @Test
    void testWrite() throws FileNotFoundException {
        // save recipe to testList file
        testWriter.write("hello");
        testWriter.write(":::");
        testWriter.write("world");
        testWriter.write("\n");
        testWriter.close();

        // verify "hello" has been added to testList.txt
        Scanner check = new Scanner(new File( "./data/testList.txt"));
        while (check.hasNext()) {
            String line = check.nextLine();
            assertEquals(line, "hello"+":::"+"world");
        }
    }
}

