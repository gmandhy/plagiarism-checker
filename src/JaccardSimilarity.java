import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity implements SimilarityCalculator {

    @Override
    public double compute(Document d1, Document d2) {
        Set<String> s1 = d1.getShingles();
        Set<String> s2 = d2.getShingles();

        if (s1.isEmpty() || s2.isEmpty()) return 0;

        Set<String> inter = new HashSet<>(s1);
        inter.retainAll(s2);

        Set<String> union = new HashSet<>(s1);
        union.addAll(s2);

        return (double) inter.size() / union.size();
    }
}
