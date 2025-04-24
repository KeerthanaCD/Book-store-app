package com.book.store.service;

import com.book.store.enums.BookTitles;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest {

    @Test
    public void testOneBook() {
        Map<String, Integer> bookCounts = Map.of(BookTitles.THE_CLEAN_CODER.getTitle(), 1);
        assertEquals(50.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testTwoDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of(BookTitles.THE_CLEAN_CODER.getTitle(), 1));
        bookCounts.put(BookTitles.TEST_DRIVEN_DEVELOPMENT.getTitle(), 1);
        assertEquals(95.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testThreeDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of(BookTitles.THE_CLEAN_CODER.getTitle(), 1));
        bookCounts.put(BookTitles.TEST_DRIVEN_DEVELOPMENT.getTitle(), 1);
        bookCounts.put(BookTitles.CLEAN_ARCHITECTURE.getTitle(), 1);
        assertEquals(135.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testFourDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of(BookTitles.THE_CLEAN_CODER.getTitle(), 1));
        bookCounts.put(BookTitles.TEST_DRIVEN_DEVELOPMENT.getTitle(), 1);
        bookCounts.put(BookTitles.CLEAN_ARCHITECTURE.getTitle(), 1);
        bookCounts.put(BookTitles.CLEAN_CODE.getTitle(), 1);
        assertEquals(160.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testFiveDifferentBooks() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of(BookTitles.THE_CLEAN_CODER.getTitle(), 1));
        bookCounts.put(BookTitles.TEST_DRIVEN_DEVELOPMENT.getTitle(), 1);
        bookCounts.put(BookTitles.CLEAN_ARCHITECTURE.getTitle(), 1);
        bookCounts.put(BookTitles.CLEAN_CODE.getTitle(), 1);
        bookCounts.put(BookTitles.WORKING_EFFECTIVELY_WITH_LEGACY_CODE.getTitle(), 1);
        assertEquals(187.5, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testMultipleBooksWithLessCount() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of(BookTitles.THE_CLEAN_CODER.getTitle(), 2));
        bookCounts.put(BookTitles.TEST_DRIVEN_DEVELOPMENT.getTitle(), 1);
        bookCounts.put(BookTitles.WORKING_EFFECTIVELY_WITH_LEGACY_CODE.getTitle(), 2);
        assertEquals(230.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }

    @Test
    public void testMultipleBooksWithMoreCount() {
        Map<String, Integer> bookCounts = new java.util.HashMap<>(Map.of(BookTitles.THE_CLEAN_CODER.getTitle(), 2));
        bookCounts.put(BookTitles.TEST_DRIVEN_DEVELOPMENT.getTitle(), 2);
        bookCounts.put(BookTitles.CLEAN_ARCHITECTURE.getTitle(), 2);
        bookCounts.put(BookTitles.CLEAN_CODE.getTitle(), 1);
        bookCounts.put(BookTitles.WORKING_EFFECTIVELY_WITH_LEGACY_CODE.getTitle(), 1);
        assertEquals(320.0, new PriceCalculator().calculateTotalPrice(bookCounts));
    }
}
