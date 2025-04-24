package com.book.store.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultDiscountStrategyTest {

    private final DefaultDiscountStrategy discountStrategy = new DefaultDiscountStrategy();

    @Test
    void testCalculateSetPriceWithValidDiscounts() {
        assertEquals(100.0, discountStrategy.calculateSetPrice(1, 100.0), 0.01);
        assertEquals(190.0, discountStrategy.calculateSetPrice(2, 100.0), 0.01);
        assertEquals(270.0, discountStrategy.calculateSetPrice(3, 100.0), 0.01);
        assertEquals(320.0, discountStrategy.calculateSetPrice(4, 100.0), 0.01);
        assertEquals(375.0, discountStrategy.calculateSetPrice(5, 100.0), 0.01);
    }
}
