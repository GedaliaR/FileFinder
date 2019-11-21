import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DirectoryIndexTest {

    @Test
    public void findFileLocation() {

        DirectoryIndex directoryIndex = new DirectoryIndex("C:\\Users\\gedal\\Desktop");

        ArrayList<String> result = (ArrayList<String>) directoryIndex.findFileLocation("test.txt");

        System.out.println(result);
    }
}