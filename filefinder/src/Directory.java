import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.util.*;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * Represents the full file hierarchy of a given root-directory. Upon instantiation Creates a Graph via JGraphT,
 * and populates it with the root fileDirectoryGraph, all the subdirectories, and all the non-fileDirectoryGraph files
 * contained therein.
 *
 * The advantages of this class is that once populated, the accessing/modifying times are significantly shorter than
 * The FileFinder class's find file method. Also, it can be helpful to have a graph modeling a file directory,
 * and this class could be modified to include more complex methods and perform more complex tasks on the Graph.
 *
 * The downside of this class is that the Graph is an expensive data structure, and normally it's unnecessary to have
 * on memory every subdirectory.
 */
public class Directory {

    private Graph<File, DefaultEdge> fileDirectoryGraph;

    /**
     * Creates and populates the Graph object.
     * @param rootDirectory The root from which the directory will be graphed, as a String (example: "C:")
     */
    public Directory(String rootDirectory){

        File fRootDirectory = new File(rootDirectory);
        if (!fRootDirectory.isDirectory())
            throw new IllegalArgumentException("Inputted file is not a directory");

        fileDirectoryGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        fileDirectoryGraph.addVertex(fRootDirectory);

        graphFileDirectory(fRootDirectory);
    }

    /**
     * Private helper to the constructor that recursively goes through every subdirectory and adds its contents to
     * the graph.
     * @param rootSubDirectory The sub-directory being added
     */
    private void graphFileDirectory(File rootSubDirectory) {
        File[] temp = rootSubDirectory.listFiles();
        if (temp != null && temp.length != 0) {
            for (File f : temp) {
                fileDirectoryGraph.addVertex(f);
                fileDirectoryGraph.addEdge(rootSubDirectory, f);
                graphFileDirectory(f);
            }
        }
    }

    /**
     * Finds a file by name if it exists in the directory graphed.
     * @param fileName The name of the file to find.
     * @return File path as an <code>ArrayList<String></code> if found; empty ArrayList if not.
     */
    public List<String> findFileLocation(String fileName){

        ArrayList<String> result = new ArrayList<>();

        Iterator<File> iterator = new BreadthFirstIterator<>(fileDirectoryGraph);
        while (iterator.hasNext()) {
            File file = iterator.next();
            if (file.getName().equals(fileName)){
                result.add(file.getPath());
            }
        }
        return result;
    }
}
