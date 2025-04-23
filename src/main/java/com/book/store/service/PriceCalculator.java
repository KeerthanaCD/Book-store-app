package com.book.store.service;

import com.book.store.strategy.DiscountStrategyFactory;
import com.book.store.strategy.IDiscountStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code PriceCalculator} class provides methods to calculate the total price of books
 * with discounts applied based on the number of unique books in a group.
 * <p>
 * It uses a discount strategy to determine the price of groups of books and employs
 * memoization to optimize the calculation of the maximum discount.
 */
public class PriceCalculator {

    /**
     * The fixed price of a single book.
     */
    private static final double BOOK_PRICE = 50.0;

    /**
     * The environment variable name for the discount strategy.
     */
    private static final String DISCOUNT_STRATEGY = "DISCOUNT_STRATEGY";

    /**
     * The discount strategy used to calculate the price of groups of books.
     */
    private final IDiscountStrategy discountStrategy;

    /**
     * Constructor to initialize the discount strategy using a factory.
     */
    public PriceCalculator() {
        String strategyType = System.getenv(DISCOUNT_STRATEGY);
        this.discountStrategy = DiscountStrategyFactory.getStrategy(strategyType);
    }

    /**
     * Calculates the total price of books with the maximum possible discount applied.
     * <p>
     * This method takes a map of book titles and their respective counts, determines
     * the optimal grouping of books to maximize discounts, and calculates the total price.
     *
     * @param bookCounts A map where the key is the book title and the value is the count of that book.
     *                   For example, {@code {"Book A": 2, "Book B": 3}} represents 2 copies of "Book A"
     *                   and 3 copies of "Book B".
     * @return The total price after applying discounts.
     */
    public double calculateTotalPrice(Map<String, Integer> bookCounts) {
        List<Integer> counts = new ArrayList<>(bookCounts.values());
        Map<List<Integer>, Double> memo = new HashMap<>(); // Memoization map
        return calculateMaxDiscount(counts, memo);
    }

    /**
     * Recursively calculates the minimum price for a given list of book counts by forming groups
     * and applying discounts.
     *
     * @param bookCounts      A list of integers representing the counts of each book.
     * @param computedResults A map to store previously computed results for specific counts.
     * @return The minimum price for the given book counts.
     */
    private double calculateMaxDiscount(List<Integer> bookCounts, Map<List<Integer>, Double> computedResults) {
        removeZeroCounts(bookCounts);

        if (bookCounts.isEmpty()) return 0.0;

        if (computedResults.containsKey(bookCounts)) {
            return computedResults.get(bookCounts);
        }

        double minPrice = Double.MAX_VALUE;

        for (int groupSize = 1; groupSize <= Math.min(5, bookCounts.size()); groupSize++) {
            List<Integer> newCounts = simulateGroupFormation(bookCounts, groupSize);
            double groupPrice = discountStrategy.calculateGroupPrice(groupSize, BOOK_PRICE);
            double remainingPrice = calculateMaxDiscount(newCounts, computedResults);
            minPrice = Math.min(minPrice, groupPrice + remainingPrice);
        }

        computedResults.put(new ArrayList<>(bookCounts), minPrice);

        return minPrice;
    }

    /**
     * Removes all zero counts from the list of book counts.
     * <p>
     * This method is used to clean up the list of counts by removing books that are no longer
     * available.
     *
     * @param counts A list of integers representing the counts of each book.
     */
    private static void removeZeroCounts(List<Integer> counts) {
        counts.removeIf(count -> count == 0);
    }

    /**
     * Simulates the formation of a group of books of a given size by decrementing the counts
     * of the first {@code groupSize} books in the list.
     *
     * @param counts    A list of integers representing the counts of each book.
     * @param groupSize The size of the group to form.
     * @return A new list of book counts after forming the group.
     */
    private static List<Integer> simulateGroupFormation(List<Integer> counts, int groupSize) {
        List<Integer> newCounts = new ArrayList<>(counts);
        for (int i = 0; i < groupSize; i++) {
            newCounts.set(i, newCounts.get(i) - 1);
        }
        return newCounts;
    }
}

