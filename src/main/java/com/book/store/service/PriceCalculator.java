package com.book.store.service;

import com.book.store.factory.DiscountStrategyFactory;
import com.book.store.strategy.IDiscountStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code PriceCalculator} class provides methods to calculate the total price of books
 * with discounts applied based on the number of unique books in a group.
 * <p>
 * It uses a discount strategy to determine the price of groups of books to optimize the calculation of the maximum discount.
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
     * This method takes a map of book titles and their respective counts, determines
     * the optimal grouping of books to maximize discounts, and calculates the total price.
     *
     * @param bookCounts book title and their count
     * @return The total price after applying discounts.
     */
    public double calculateTotalPrice(Map<String, Integer> bookCounts) {
        List<Integer> counts = new ArrayList<>(bookCounts.values());
        Map<List<Integer>, Double> computedResults = new HashMap<>();
        return calculateMaxDiscount(counts, computedResults);
    }

    /**
     * Recursively calculates the minimum price for a given list of book counts by forming groups
     * and applying discounts.
     *
     * @param bookCounts      A list of integers representing the counts of each book.
     * @param computedGroups A map to store previously computed results for specific counts.
     * @return The minimum price for the given book counts.
     */
    private double calculateMaxDiscount(List<Integer> bookCounts, Map<List<Integer>, Double> computedGroups) {
        bookCounts.removeIf(count -> count == 0);

        if (bookCounts.isEmpty()) return 0.0;

        if (computedGroups.containsKey(bookCounts)) {
            return computedGroups.get(bookCounts);
        }

        double minPrice = Double.MAX_VALUE;

        for (int groupSize = 1; groupSize <= Math.min(5, bookCounts.size()); groupSize++) {
            List<Integer> newCounts = getGroupFormation(bookCounts, groupSize);
            double groupPrice = discountStrategy.calculateSetPrice(groupSize, BOOK_PRICE);
            double remainingPrice = calculateMaxDiscount(newCounts, computedGroups);
            minPrice = Math.min(minPrice, groupPrice + remainingPrice);
        }

        computedGroups.put(new ArrayList<>(bookCounts), minPrice);

        return minPrice;
    }

    /**
     * Creates the group formation of books of a given size by decrementing the counts
     * of the first {@code groupSize} books in the list.
     *
     * @param counts    A list of integers representing the counts of each book.
     * @param groupSize The size of the group to form.
     * @return A new list of book counts after forming the group.
     */
    private static List<Integer> getGroupFormation(List<Integer> counts, int groupSize) {
        List<Integer> newCounts = new ArrayList<>(counts);
        for (int i = 0; i < groupSize; i++) {
            newCounts.set(i, newCounts.get(i) - 1);
        }
        return newCounts;
    }
}

