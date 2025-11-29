import java.util.List;
import java.util.Set;

public class AnalysisResult {
    public final String file1;
    public final String file2;
    public final double cosineScore;
    public final double jaccardScore;
    public final List<String> tokens1;
    public final List<String> tokens2;
    public final Set<String> matches2;
    public final Set<String> matches3;

    public AnalysisResult(String file1, String file2,
                          double cosineScore, double jaccardScore,
                          List<String> tokens1, List<String> tokens2,
                          Set<String> matches2, Set<String> matches3) {
        this.file1 = file1;
        this.file2 = file2;
        this.cosineScore = cosineScore;
        this.jaccardScore = jaccardScore;
        this.tokens1 = tokens1;
        this.tokens2 = tokens2;
        this.matches2 = matches2;
        this.matches3 = matches3;
    }
}
