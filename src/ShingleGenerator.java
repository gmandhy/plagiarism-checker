import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShingleGenerator {

    private final int k;

    public ShingleGenerator(int k) {
        this.k = k;
    }

    public Set<String> generate(List<String> tokens) {
        Set<String> out = new HashSet<>();
        if (tokens.size() < k) return out;

        for (int i = 0; i <= tokens.size() - k; i++) {
            out.add(String.join(" ",
                    tokens.subList(i, i + k)));
        }
        return out;
    }
}
