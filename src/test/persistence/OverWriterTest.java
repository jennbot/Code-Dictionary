package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverWriterTest {
    private PrintWriter testpw;
    private FileWriter testfw;
    private String testfile = "./data/termList.txt";

    @BeforeEach
    void runBefore() throws IOException {
        testfw = new FileWriter(new File(testfile));
        testpw = new PrintWriter(testfw);
    }

    @Test
    void testOverWrite() throws IOException {
        // add recipe to list
        testpw.write("hello");
        testpw.write(":::");
        testpw.write("world");

        // reset list to default
        OverWriter testow = new OverWriter();
        testow.overWrite();

        // verify default list has loaded
        Scanner check = new Scanner(new File(testfile));
        while (check.hasNext()) {
            String line = check.nextLine();
            assertEquals(line,
                    "String:::A variable that contains a collection of characters surrounded " +
                            "by double quotes ---> \"string\", \"hello\", \"world\"");
        }

    }
}
