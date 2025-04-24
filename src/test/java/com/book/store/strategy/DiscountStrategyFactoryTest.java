package com.book.store.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountStrategyFactoryTest {

    @Test
    void testDefaultStrategy() {
        IDiscountStrategy strategy = DiscountStrategyFactory.getStrategy("DEFAULT");
        assertNotNull(strategy);
        assertInstanceOf(DefaultDiscountStrategy.class, strategy);
    }

    @Test
    void testStrategyWithNullValue() {
        IDiscountStrategy strategy = DiscountStrategyFactory.getStrategy(null);
        assertNotNull(strategy);
        assertInstanceOf(DefaultDiscountStrategy.class, strategy);
    }

    @Test
    void testStrategyWithEmptyValue() {
        IDiscountStrategy strategy = DiscountStrategyFactory.getStrategy("");
        assertNotNull(strategy);
        assertInstanceOf(DefaultDiscountStrategy.class, strategy);
    }

    @Test
    void testStrategyWithInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            DiscountStrategyFactory.getStrategy("TEST")
        );
        assertEquals("Unsupported discount strategy: TEST", exception.getMessage());
    }
}
