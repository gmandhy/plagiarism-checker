public class PorterStemmer {

    public String stem(String word) {
        // Simple Porter stemmer (compact version)
        word = word.toLowerCase();

        if (word.endsWith("ing") && word.length() > 5)
            return word.substring(0, word.length() - 3);
        if (word.endsWith("ed") && word.length() > 4)
            return word.substring(0, word.length() - 2);
        if (word.endsWith("es") && word.length() > 4)
            return word.substring(0, word.length() - 2);
        if (word.endsWith("s") && word.length() > 3)
            return word.substring(0, word.length() - 1);

        return word;
    }
}
