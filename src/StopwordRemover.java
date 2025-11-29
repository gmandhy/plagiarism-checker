import java.util.*;

public class StopwordRemover {

    private static final Set<String> STOPWORDS = Set.of(
            "the","is","are","a","an","and","or","of","on","in","to","for","with","that",
            "this","these","those","their","from","by","as","be","was","were","it","its",
            "at","but","not","which","without","being"
    );

    public List<String> removeStopwords(List<String> tokens) {
        List<String> cleaned = new ArrayList<>();
        for (String t : tokens) {
            if (!STOPWORDS.contains(t)) {
                cleaned.add(t);
            }
        }
        return cleaned;
    }
}
