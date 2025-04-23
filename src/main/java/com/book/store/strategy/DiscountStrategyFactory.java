package com.book.store.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class to create and manage discount strategies.
 */
public class DiscountStrategyFactory {

    private static final Map<String, IDiscountStrategy> strategies = new HashMap<>();
    private static final String DEFAULT_STRATEGY = "DEFAULT";

    static {
        strategies.put(DEFAULT_STRATEGY, new DefaultDiscountStrategy());
    }

    /**
     * Retrieves the discount strategy based on the given type.
     *
     * @param strategyType The type of the discount strategy.
     * @return The corresponding discount strategy.
     * @throws IllegalArgumentException If the strategy type is not supported.
     */
    public static IDiscountStrategy getStrategy(String strategyType) {
        if (strategyType == null || strategyType.isEmpty()) {
            strategyType = DEFAULT_STRATEGY;
        }
        IDiscountStrategy strategy = strategies.get(strategyType.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported discount strategy: " + strategyType);
        }
        return strategy;
    }

}
