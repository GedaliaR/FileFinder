import java.util.List;

import static org.junit.Assert.*;

public class FileFinderTest {

    @org.junit.Test
    public void findFileLocation() {
        FileFinder fileFinder = new FileFinder();

        List<String> result = fileFinder.findFileLocation("test.txt", "C:\\Users\\gedal\\Desktop");

        System.out.println(result);
    }
}