package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriterTest {
    private PrintWriter testWriter;
    private FileWriter testFileWriter;

    @BeforeEach
    void runBefore() throws IOException {
        String testlist = "./data/testList";
        testFileWriter = new FileWriter(testlist, true);
        testWriter = new PrintWriter(testFileWriter);
    }

//    @Test
//    void testConstructor() {
//    }

    @Test
    void testWrite() throws FileNotFoundException {
        // save recipe to testList file
        testWriter.write("hello");
        testWriter.write(":::");
        testWriter.write("world");
        testWriter.write("\n");
        testWriter.close();

        // verify "hello" has been added to testList.txt
        Scanner check = new Scanner(new File( "./data/testList"));
        while (check.hasNext()) {
            String line = check.nextLine();
            assertEquals(line, "hello"+":::"+"world");
        }
    }
}

