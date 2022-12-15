package com.findwise;

import java.util.*;

class SearchEngineImpl implements SearchEngine {

    private Map<String, Set<IndexEntry>> indexedData = new TreeMap<>();
    private Map<String, String> rawData = new HashMap<>();

    @Override
    public void indexDocument(String id, String content) {
        buildRawData(id, content);
        buildIndexedData(id, content);
        calculateScore();
        sortIndexedDataValues();
    }

    @Override
    public List<IndexEntry> search(String term) {
        if (!this.indexedData.containsKey(term)) {
            throw new RuntimeException("Searching term does not exist");
        }
        return new ArrayList<>(this.indexedData.get(term));
    }

    private void buildRawData(String id, String content) {
        rawData.put(id, content);
    }

    private void buildIndexedData(String id, String content) {
        String[] tokens = content.split("\\s+");

        for (String token : tokens) {
            if (indexedData.containsKey(token)) {
                Set<IndexEntry> data = indexedData.get(token);
                data.add(new Entry(id));
                indexedData.put(token, data);
            } else {
                Set<IndexEntry> data2 = new LinkedHashSet<>();
                data2.add(new Entry(id));
                indexedData.put(token, data2);
            }
        }
    }

    private void calculateScore() {
        this.rawData.forEach((keyRaw, valueRaw) -> {
            String[] tokens = valueRaw.split("\\s+");
            this.indexedData.forEach((key, value) -> {
                value.stream().filter(indexEntry -> indexEntry.getId().equals(keyRaw)).forEach(entry -> {
                    double tfIdf = TfIdf.tfIdf(Arrays.asList(tokens), this.indexedData, key.toLowerCase());
                    entry.setScore(tfIdf);
                });
            });
        });
    }

    private void sortIndexedDataValues() {
        Sort.sort(this.indexedData);
    }

}
