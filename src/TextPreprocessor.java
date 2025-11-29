public class TextPreprocessor {

    public String normalize(String text) {
        if (text == null) return "";

        // convert to lowercase first
        String lower = text.toLowerCase();

        // keep: a–z, numbers, and spaces
        // remove everything else (punctuation, symbols)
        String cleaned = lower.replaceAll("[^a-z0-9 ]", " ");

        // collapse extra spaces
        cleaned = cleaned.trim().replaceAll("\\s+", " ");

        return cleaned;
    }

}
