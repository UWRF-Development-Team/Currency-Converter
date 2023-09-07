
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// This class is from theoliverlear's Java tools library.
/**
 * This class is used to retrieve data from a file.
 */
public class FileLineRetriever {
    int targetLineIndex;
    String filePath;
    String data;
    /**
     * This constructor takes a target line index and a file path as arguments.
     * @param targetLineIndex
     * @param filePath
     */
    public FileLineRetriever(int targetLineIndex, String filePath) {
        this.targetLineIndex = targetLineIndex;
        this.filePath = filePath;
        this.data = "";
        this.fetchData();
    }
    /**
     * This method retrieves line data from a file.
     */
    public void fetchData() {
        String line = "";
        try {
            int lineCounter = 0;
            Scanner fileReader = new Scanner(new File(this.filePath));
            while (fileReader.hasNext()) {
                line = fileReader.nextLine();
                if (lineCounter == this.targetLineIndex) {
                    this.data = line;
                }
                lineCounter++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method returns the data retrieved from the file.
     * @return String
     */
    public String getData() {
        return this.data;
    }
}
