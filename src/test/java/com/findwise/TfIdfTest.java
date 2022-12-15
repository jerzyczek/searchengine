package com.findwise;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for class performed in SearchEngineImplTest
 */
class TfIdfTest {

    @Test
    void tfIdf_EmptyList_ThrowException() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> TfIdf.tfIdf(new ArrayList<>(), new TreeMap<>(), "test"));
        assertEquals("Not valid data", exception.getMessage());
    }

}