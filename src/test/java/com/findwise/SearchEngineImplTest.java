package com.findwise;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchEngineImplTest {

    private final SearchEngine searchEngine = indexDocument();

    @Test
    void search_TermExistInData_ReturnListWithDocuments() {
        List<IndexEntry> indexEntries = searchEngine.search("fox");
        assertEquals(2, indexEntries.size());
        assertEquals("document3", indexEntries.get(0).getId());
        assertEquals("document1", indexEntries.get(1).getId());
        assertEquals(0.25596563846115067, indexEntries.get(0).getScore());
        assertEquals(0.22396993365350687, indexEntries.get(1).getScore());
    }

    @Test
    void search_TermNotExistInData_ThrowException() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> searchEngine.search("test"));
        assertEquals("Searching term does not exist", exception.getMessage());
    }

    private SearchEngine indexDocument() {
        SearchEngine searchEngine = new SearchEngineImpl();
        searchEngine.indexDocument("document1", "the brown fox jumped over the brown dog");
        searchEngine.indexDocument("document2", "the lazy brown dog sat in the corner");
        searchEngine.indexDocument("document3", "the red fox bit the lazy dog");
        return searchEngine;
    }

}