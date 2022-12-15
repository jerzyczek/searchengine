package com.findwise;

import java.util.List;
import java.util.Map;
import java.util.Set;

class TfIdf {

    private TfIdf() {
    }

    public static double tfIdf(List<String> doc, Map<String, Set<IndexEntry>> docs, String term) {
        validateData(doc, docs, term);
        return tf(doc, term) * idf(docs, term);
    }

    private static double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

    private static double idf(Map<String, Set<IndexEntry>> docs, String term) {
        if (!docs.containsKey(term)) {
            throw new RuntimeException("Searching term does not exist");
        }

        Set<IndexEntry> keyValue = docs.get(term);
        double n = keyValue.size();

        return Math.log(docs.size() / n);
    }

    private static void validateData(List<String> doc, Map<String, Set<IndexEntry>> docs, String term) {
        if (doc.isEmpty() || docs.isEmpty() || term == null || term.isEmpty()) {
            throw new RuntimeException("Not valid data");
        }
    }

}
