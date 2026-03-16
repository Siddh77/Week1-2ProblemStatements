import java.util.*;

class PlagiarismDetector {

    private Map<String, Set<String>> ngramIndex = new HashMap<>();
    private int N = 5;

    private List<String> getNgrams(String text) {

        String[] words = text.split(" ");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = i; j < i + N; j++)
                sb.append(words[j]).append(" ");

            grams.add(sb.toString().trim());
        }

        return grams;
    }

    public void indexDocument(String docId, String text) {

        for (String gram : getNgrams(text)) {

            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }

    public Map<String, Integer> analyzeDocument(String text) {

        Map<String, Integer> matches = new HashMap<>();

        for (String gram : getNgrams(text)) {

            if (ngramIndex.containsKey(gram)) {

                for (String doc : ngramIndex.get(gram))
                    matches.put(doc, matches.getOrDefault(doc, 0) + 1);
            }
        }

        return matches;
    }
}
