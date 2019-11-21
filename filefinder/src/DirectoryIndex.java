import java.io.File;
import java.util.*;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * This represents an index of every non-directory file within a given directory. Upon instantiation, creates an
 * ArrayList and populates it with only the bottom level files.
 *
 * This class shares the same basic advantages over the simple FileFinder class with the directory,
 * in terms of fast repeated access/modification.
 *
 * The advantage of this class over the Directory class is that the underlying ArrayList in this class is smaller than
 * the Graph in directory. The potential disadvantage is that you lose immediate access to the File objects that
 * form the file hierarchy. This class can also be modified to perform more complex tasks by adding more methods.
 */
public class DirectoryIndex {

    private ArrayList<File> index;

    public DirectoryIndex(String rootDirectory){
        index = new ArrayList<>();

        File fRootDirectory = new File(rootDirectory);
        if (!fRootDirectory.isDirectory())
            throw new IllegalArgumentException("Inputted directory is not a directory");

        addFilesToIndex(fRootDirectory);
    }

    private void addFilesToIndex(File file) {
        if (file.isDirectory()) {
            File[] temp = file.listFiles();
            for (File f : temp) {
                addFilesToIndex(f);
            }
        }
        else
            index.add(file);
    }

    public List<String> findFileLocation(String fileName){

        ArrayList<String> result = new ArrayList<>();

        for(File f : index){
            if (f.getName().equals(fileName))
                result.add(f.getPath());
        }

        return result;
    }
}
