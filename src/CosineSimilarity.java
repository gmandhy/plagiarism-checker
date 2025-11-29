import java.util.Map;

public class CosineSimilarity {

    public double compute(Map<String, Double> v1, Map<String, Double> v2) {
        double dot = 0.0;
        double mag1 = 0.0;
        double mag2 = 0.0;

        for (String term : v1.keySet()) {
            double x = v1.get(term);
            double y = v2.get(term);

            dot += x * y;
            mag1 += x * x;
            mag2 += y * y;
        }

        if (mag1 == 0 || mag2 == 0) return 0;

        return dot / (Math.sqrt(mag1) * Math.sqrt(mag2));
    }
}
