import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    public List<String> tokenize(String text) {
        if (text.isBlank()) return List.of();
        return Arrays.asList(text.split(" "));
    }
}
