package com.book.store.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultDiscountStrategyTest {

    private final DefaultDiscountStrategy discountStrategy = new DefaultDiscountStrategy();

    @Test
    void testCalculateGroupPriceWithValidDiscounts() {
        assertEquals(100.0, discountStrategy.calculateGroupPrice(1, 100.0), 0.01);
        assertEquals(190.0, discountStrategy.calculateGroupPrice(2, 100.0), 0.01);
        assertEquals(270.0, discountStrategy.calculateGroupPrice(3, 100.0), 0.01);
        assertEquals(320.0, discountStrategy.calculateGroupPrice(4, 100.0), 0.01);
        assertEquals(375.0, discountStrategy.calculateGroupPrice(5, 100.0), 0.01);
    }

    @Test
    void testCalculateGroupPriceWithNoDiscount() {
        assertEquals(600.0, discountStrategy.calculateGroupPrice(6, 100.0), 0.01);
    }
}
