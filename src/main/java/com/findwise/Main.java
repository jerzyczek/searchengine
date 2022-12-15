package com.findwise;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngineImpl();
        searchEngine.indexDocument("document1", "the brown fox jumped over the brown dog");
        searchEngine.indexDocument("document2", "the lazy brown dog sat in the corner");
        searchEngine.indexDocument("document3", "the red fox bit the lazy dog");

        List<IndexEntry> documents = searchEngine.search(args[0]);

        System.out.println("Result of searching:");
        documents.forEach(System.out::println);
    }
}