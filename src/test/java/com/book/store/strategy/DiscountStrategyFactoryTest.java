package com.book.store.strategy;

import com.book.store.factory.DiscountStrategyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DiscountStrategyFactoryTest {

    @ParameterizedTest
    @CsvSource({
        "DEFAULT, com.book.store.strategy.DefaultDiscountStrategy",
        "'', com.book.store.strategy.DefaultDiscountStrategy",
        "null, com.book.store.strategy.DefaultDiscountStrategy"
    })
    void testDefaultStrategies(String input, String expectedClassName) throws ClassNotFoundException {
        IDiscountStrategy strategy = DiscountStrategyFactory.getStrategy("null".equals(input) ? null : input);
        assertNotNull(strategy);
        assertEquals(Class.forName(expectedClassName), strategy.getClass());
    }

    @Test
    void testStrategyWithInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            DiscountStrategyFactory.getStrategy("TEST")
        );
        assertEquals("Unsupported discount strategy: TEST", exception.getMessage());
    }
}
