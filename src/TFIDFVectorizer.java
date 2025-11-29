import java.util.*;

public class TFIDFVectorizer {

    // Token frequency per document
    public Map<String, Integer> termFrequency(List<String> tokens) {
        Map<String, Integer> freq = new HashMap<>();
        for (String t : tokens) {
            freq.put(t, freq.getOrDefault(t, 0) + 1);
        }
        return freq;
    }

    // Compute IDF for all terms across both documents
    public Map<String, Double> computeIDF(Map<String, Integer> d1, Map<String, Integer> d2) {
        Map<String, Double> idf = new HashMap<>();

        Set<String> allTerms = new HashSet<>();
        allTerms.addAll(d1.keySet());
        allTerms.addAll(d2.keySet());

        int N = 2; // number of documents

        for (String term : allTerms) {
            int df = 0;
            if (d1.containsKey(term)) df++;
            if (d2.containsKey(term)) df++;

            // smooth IDF
            double value = Math.log((N + 1.0) / (df + 1.0)) + 1.0;

            idf.put(term, value);
        }

        return idf;
    }


    // Create TF-IDF vector (term → weight)
    public Map<String, Double> buildVector(Map<String, Integer> tf, Map<String, Double> idf) {
        Map<String, Double> tfidf = new HashMap<>();

        for (String term : idf.keySet()) {
            double tfValue = tf.getOrDefault(term, 0);
            double idfValue = idf.get(term);
            tfidf.put(term, tfValue * idfValue);
        }

        return tfidf;
    }
}
