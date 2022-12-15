package com.findwise;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void sort_ProvidedUnsortedData_ReturnSortedData() {
        Map<String, Set<IndexEntry>> sortedData = buildDataStructure();
        Sort.sort(sortedData);
        Set<IndexEntry> sortedEntries = sortedData.get("test");
        List<IndexEntry> sortedEntriesList = new ArrayList<>(sortedEntries);
        assertEquals("document3", sortedEntriesList.get(0).getId());
        assertEquals("document4", sortedEntriesList.get(1).getId());
        assertEquals("document2", sortedEntriesList.get(2).getId());
        assertEquals("document1", sortedEntriesList.get(3).getId());
    }

    @Test
    void sort_ProvidedDataIsEmpty_ThrowException() {
        Map<String, Set<IndexEntry>> sortedData = new TreeMap<>();

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> Sort.sort(sortedData));
        assertEquals("Data is empty", exception.getMessage());
    }

    private Map<String, Set<IndexEntry>> buildDataStructure() {
        Map<String, Set<IndexEntry>> data = new TreeMap<>();
        Set<IndexEntry> entries = buildIndexes();
        data.put("test", entries);
        return data;
    }

    private Set<IndexEntry> buildIndexes() {
        Set<IndexEntry> entries = new LinkedHashSet<>();
        entries.add(new Entry("document1", 2.1112333));
        entries.add(new Entry("document4", 5.1322333));
        entries.add(new Entry("document3", 6.132112333));
        entries.add(new Entry("document2", 3.11222333));
        return entries;
    }

}