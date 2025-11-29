import java.util.List;
import java.util.Map;

public class HeatmapHighlighting {

    /**
     * Highlights words based on TF-IDF weight threshold.
     */
    public String highlightWithTFIDF(List<String> tokens, Map<String, Double> tfidf, double threshold) {
        StringBuilder sb = new StringBuilder();

        for (String tok : tokens) {
            double weight = tfidf.getOrDefault(tok, 0.0);

            if (weight >= threshold) {
                // stronger color = higher TF-IDF
                int intensity = (int) Math.min(255, 150 + weight * 40);
                int red = 255;
                int greenBlue = Math.max(0, 255 - intensity);

                sb.append(
                        "<span style='background-color: rgb(" + red + "," + greenBlue + "," + greenBlue +
                                "); padding:2px; margin:2px; border-radius:4px;'>"
                ).append(tok).append("</span> ");
            } else {
                sb.append(tok).append(" ");
            }
        }

        return sb.toString();
    }
}
