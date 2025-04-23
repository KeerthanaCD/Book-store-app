package com.book.store.strategy;

import java.util.Map;

/**
 * The {@code DefaultDiscountStrategy} class implements the default discount logic
 * based on the number of unique books in a group.
 */
public class DefaultDiscountStrategy implements IDiscountStrategy {
    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.0,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

    @Override
    public double calculateGroupPrice(int groupSize, double bookPrice) {
        return groupSize * bookPrice * (1 - DISCOUNTS.getOrDefault(groupSize, 0.0));
    }
}
