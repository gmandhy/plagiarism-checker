import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HTMLReportGenerator {

    public void generateReport(
            String file1Name,
            String file2Name,
            String raw1,
            String raw2,
            double cosine,
            double jaccard,
            Set<String> matches2,
            Set<String> matches3,
            List<String> stemmed1,
            List<String> stemmed2,
            Map<String, Double> vec1,
            Map<String, Double> vec2
    ) throws Exception {

        FileWriter fw = new FileWriter("report.html");

        fw.write("""
<!DOCTYPE html>
<html>
<head>
<title>Plagiarism Report</title>
<style>
body { font-family: Arial; margin: 40px; }
.card { background: #fafafa; padding: 20px; border-radius: 10px; margin-top: 20px; }
h2 { margin-top: 40px; }
.highlight { background-color: #ffcccc; border-radius: 4px; padding: 2px; }
</style>
</head>
<body>
""");

        fw.write("<h1>Plagiarism Check Report</h1>");

        fw.write("<h3>Semantic Similarity (TF-IDF Cosine): " +
                String.format("%.2f", cosine) + "%</h3>");

        fw.write("<h3>Lexical Similarity (Shingles Jaccard): " +
                String.format("%.2f", jaccard) + "%</h3>");

        fw.write("<h2>Matched 2-word Phrases</h2>");
        for (String m : matches2) fw.write("- " + m + "<br>");

        fw.write("<h2>Matched 3-word Phrases</h2>");
        for (String m : matches3) fw.write("- " + m + "<br>");

        fw.write("<h2>Document Comparison (semantic heatmap)</h2>");

        fw.write("<div class='card'><b>" + file1Name + "</b><br><br>");
        fw.write(highlightRawText(raw1, stemmed1));
        fw.write("</div>");

        fw.write("<div class='card'><b>" + file2Name + "</b><br><br>");
        fw.write(highlightRawText(raw2, stemmed2));
        fw.write("</div>");

        fw.write("</body></html>");

        fw.close();
    }

    private String highlightRawText(String raw, List<String> stemmedTokens) {

        String[] words = raw.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String w : words) {
            String normalized = w.toLowerCase()
                    .replaceAll("[^a-z]", ""); // normalize punctuation

            boolean match = false;

            for (String st : stemmedTokens) {
                if (normalized.startsWith(st)) {
                    match = true;
                    break;
                }
            }

            if (match)
                sb.append("<span class='highlight'>").append(w).append("</span> ");
            else
                sb.append(w).append(" ");
        }

        return sb.toString();
    }
}
