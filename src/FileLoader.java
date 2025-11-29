import java.nio.file.Files;
import java.nio.file.Path;

public class FileLoader {

    public String load(String filePath) throws Exception {
        return Files.readString(Path.of(filePath));
    }
}
