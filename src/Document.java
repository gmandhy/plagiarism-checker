import java.util.List;
import java.util.Set;

public class Document {
    private final String rawText;
    private List<String> tokens;
    private Set<String> shingles;

    public Document(String rawText) {
        this.rawText = rawText;
    }

    public String getRawText() { return rawText; }
    public List<String> getTokens() { return tokens; }
    public void setTokens(List<String> tokens) { this.tokens = tokens; }
    public Set<String> getShingles() { return shingles; }
    public void setShingles(Set<String> shingles) { this.shingles = shingles; }
}
