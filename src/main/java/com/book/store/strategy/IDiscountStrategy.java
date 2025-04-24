package com.book.store.strategy;

/**
 * The {@code DiscountStrategy} interface defines the contract for calculating
 * the price of a group of books based on its size and applicable discounts. Custom
 * implementation of discount strategy can be done implementing the interface.
 */
public interface IDiscountStrategy {
    /**
     * Calculates the price of a group of books based on its size and the applicable discount.
     *
     * @param groupSize The size of the group.
     * @param bookPrice The price of a single book.
     * @return The price of the group after applying the discount.
     */
    double calculateSetPrice(int groupSize, double bookPrice);
}
