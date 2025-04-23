package com.book.store.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountStrategyFactoryTest {

    @Test
    void testGetDefaultStrategy() {
        IDiscountStrategy strategy = DiscountStrategyFactory.getStrategy("DEFAULT");
        assertNotNull(strategy);
        assertInstanceOf(DefaultDiscountStrategy.class, strategy);
    }

    @Test
    void testGetStrategyWithNullType() {
        IDiscountStrategy strategy = DiscountStrategyFactory.getStrategy(null);
        assertNotNull(strategy);
        assertInstanceOf(DefaultDiscountStrategy.class, strategy);
    }

    @Test
    void testGetStrategyWithUnsupportedType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            DiscountStrategyFactory.getStrategy("TEST")
        );
        assertEquals("Unsupported discount strategy: UNSUPPORTED", exception.getMessage());
    }
}
