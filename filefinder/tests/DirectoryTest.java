import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DirectoryTest {

    @Test
    public void findFileLocation() {
        Directory directory = new Directory("C:\\Users\\gedal\\Desktop");

        ArrayList<String> result = (ArrayList<String>) directory.findFileLocation("test.txt");

        System.out.println(result);
    }
}