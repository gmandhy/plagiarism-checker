public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java Main <file1> <file2>");
            return;
        }

        try {
            AnalysisResult result = PlagiarismAnalyzer.analyze(args[0], args[1]);

            System.out.printf("Semantic Similarity (TF-IDF Cosine): %.2f%%%n", result.cosineScore);
            System.out.printf("Lexical Similarity (Shingles Jaccard): %.2f%%%n", result.jaccardScore);
            System.out.println("HTML report generated as report.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
