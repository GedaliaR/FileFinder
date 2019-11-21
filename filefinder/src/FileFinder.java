import java.io.*;
import java.util.*;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * A direct recursive implementation of the method header as was posted to the Assignment specs.
 */
public class FileFinder {

    private ArrayList<String> result = new ArrayList<>();

    public List<String> findFileLocation(String fileName, String startDirectory) {
        File rootDirectory = new File(startDirectory);

        if (!rootDirectory.isDirectory())
            throw new IllegalArgumentException("Inputted directory is not a directory");

        mapDirectory(rootDirectory, fileName);

        return result;
    }

    private void mapDirectory (File file, String fileName){
        File[] temp = file.listFiles();

        if (file.isDirectory()) {
            for (File f : temp) {
                mapDirectory(f, fileName);
            }
        }
        else if (file.getName().equals(fileName))
            result.add(file.getPath());
    }
}