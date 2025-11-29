import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlagiarismAnalyzer {

    public static AnalysisResult analyze(String path1, String path2) throws Exception {

        // helpers
        FileLoader loader = new FileLoader();
        TextPreprocessor pre = new TextPreprocessor();
        Tokenizer tokenizer = new Tokenizer();
        StopwordRemover stop = new StopwordRemover();
        PorterStemmer stem = new PorterStemmer();
        ShingleGenerator shingler = new ShingleGenerator(2);  // k = 2 now
        SimilarityCalculator jaccard = new JaccardSimilarity();
        TFIDFVectorizer vectorizer = new TFIDFVectorizer();
        CosineSimilarity cosine = new CosineSimilarity();
        PhraseMatcher pm = new PhraseMatcher();
        HTMLReportGenerator generator = new HTMLReportGenerator();

        // raw text
        String raw1 = loader.load(path1);
        String raw2 = loader.load(path2);

        // normalize
        String norm1 = pre.normalize(raw1);
        String norm2 = pre.normalize(raw2);

        // tokenize
        List<String> tokens1 = tokenizer.tokenize(norm1);
        List<String> tokens2 = tokenizer.tokenize(norm2);

        // stopwords
        tokens1 = stop.removeStopwords(tokens1);
        tokens2 = stop.removeStopwords(tokens2);

        // stemming
        tokens1 = tokens1.stream().map(stem::stem).toList();
        tokens2 = tokens2.stream().map(stem::stem).toList();

        // docs
        Document d1 = new Document(raw1);
        Document d2 = new Document(raw2);
        d1.setTokens(tokens1);
        d2.setTokens(tokens2);

        // TF-IDF
        Map<String,Integer> tf1 = vectorizer.termFrequency(tokens1);
        Map<String,Integer> tf2 = vectorizer.termFrequency(tokens2);
        Map<String,Double> idf = vectorizer.computeIDF(tf1, tf2);
        Map<String,Double> vec1 = vectorizer.buildVector(tf1, idf);
        Map<String,Double> vec2 = vectorizer.buildVector(tf2, idf);

        double cosineScore = cosine.compute(vec1, vec2) * 100.0;

        // shingles (lexical)
        d1.setShingles(shingler.generate(tokens1));
        d2.setShingles(shingler.generate(tokens2));
        double jaccardScore = jaccard.compute(d1, d2) * 100.0;

        // phrase matches
        Set<String> matches2 = pm.findMatches(tokens1, tokens2, 2);
        Set<String> matches3 = pm.findMatches(tokens1, tokens2, 3);

        // generate HTML (now also uses TF-IDF vectors for heatmap)
        generator.generateReport(
                path1,
                path2,
                raw1,          // raw document text 1
                raw2,          // raw document text 2
                cosineScore,
                jaccardScore,
                matches2,
                matches3,
                tokens1,       // stemmed tokens for semantic analysis
                tokens2,
                vec1,
                vec2
        );


        // pack into result object
        return new AnalysisResult(
                path1, path2,
                cosineScore, jaccardScore,
                tokens1, tokens2,
                matches2, matches3
        );
    }
}
