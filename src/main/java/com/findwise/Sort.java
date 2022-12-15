package com.findwise;

import java.util.*;

class Sort {

    private Sort() {
    }

    public static void sort(Map<String, Set<IndexEntry>> indexedData) {
        if (indexedData.isEmpty()) {
            throw new RuntimeException("Data is empty");
        }

        indexedData.forEach((key, value) -> {
            List<IndexEntry> indexEntries = new ArrayList<>(value);
            indexEntries.sort(Comparator.comparing(IndexEntry::getScore).reversed());
            value.clear();
            value.addAll(indexEntries);
        });
    }

}
