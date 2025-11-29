import java.util.*;

public class PhraseMatcher {

    /**
     * Finds exact matching 2-word or 3-word phrases between two token lists.
     */
    public Set<String> findMatches(List<String> tokens1, List<String> tokens2, int windowSize) {

        Set<String> matches = new HashSet<>();

        if (tokens1.size() < windowSize || tokens2.size() < windowSize) {
            return matches;
        }

        // Build shingles from doc1
        Set<String> doc1Shingles = new HashSet<>();
        for (int i = 0; i <= tokens1.size() - windowSize; i++) {
            String phrase = String.join(" ", tokens1.subList(i, i + windowSize));
            doc1Shingles.add(phrase);
        }

        // Build shingles from doc2 and compare
        for (int i = 0; i <= tokens2.size() - windowSize; i++) {
            String phrase = String.join(" ", tokens2.subList(i, i + windowSize));
            if (doc1Shingles.contains(phrase)) {
                matches.add(phrase);
            }
        }

        return matches;
    }
}
