package com.book.store;

import com.book.store.service.PriceCalculator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest {

    @Test
    public void testSingleBook() {
        Map<String, Integer> bookCounts = Map.of("Book 1", 1);
        assertEquals(50.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testTwoDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of("Book 1", 1));
        bookCounts.put("Book 2", 1);
        assertEquals(95.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testThreeDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of("Book 1", 1));
        bookCounts.put("Book 2", 1);
        bookCounts.put("Book 3", 1);
        assertEquals(135.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testFourDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of("Book 1", 1));
        bookCounts.put("Book 2", 1);
        bookCounts.put("Book 3", 1);
        bookCounts.put("Book 4", 1);
        assertEquals(160.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testFiveDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of("Book 1", 1));
        bookCounts.put("Book 2", 1);
        bookCounts.put("Book 3", 1);
        bookCounts.put("Book 4", 1);
        bookCounts.put("Book 5", 1);
        assertEquals(187.5, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testMultipleBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of("Book 1", 2));
        bookCounts.put("Book 2", 2);
        bookCounts.put("Book 3", 2);
        bookCounts.put("Book 4", 1);
        bookCounts.put("Book 5", 1);
        assertEquals(320.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }
}
