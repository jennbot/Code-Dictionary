package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;

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
    void testWrite() {
        // save recipe to testList file
        testWriter.write("hello");
        testWriter.write(":::");
        testWriter.write("world");
        testWriter.write("\n");
        testWriter.close();

        // create new hashmap and load it with testList.txt
        HashMap testlist = new HashMap<String, String>();
        String fullFilePath = "./data/testList";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fullFilePath));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":::");
                if (parts.length >= 2) {
                    String term = parts[0];
                    String defn = parts[1];
                    testlist.put(term, defn);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // verify "hello" has been added to testList.txt
        assertTrue(testlist.containsKey("scooby scooby doo"));
        assertEquals(testlist.get("scooby scooby doo"),"where are you");
        assertTrue(testlist.containsKey("hello"));
        assertEquals(testlist.get("hello"),"world");
    }
}

