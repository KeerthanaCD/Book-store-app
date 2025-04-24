package com.book.store;

import com.book.store.enums.BookTitles;
import com.book.store.service.PriceCalculator;
import com.book.store.config.LoggerConfig; // Import LoggerConfig

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.*;

/**
 * BookStoreApplication is the main class for the book store application.
 * It allows users to select books and calculates the total price after applying discounts.
 */
public class BookStoreApplication {

    // Initialize logger using LoggerConfig
    private static final Logger logger = LoggerConfig.getLogger(BookStoreApplication.class.getName());

    /**
     * Main method to run the book store application.
     */
    public static void main(String[] args) {
        logger.info("Welcome to the book store!");

        // Display available books
        logger.info("Available books with base price: 50 EUR");
        for (BookTitles bookTitle : BookTitles.values()) {
            logger.info("- " + bookTitle.getTitle());
        }

        // Accept user input for book counts
        Map<String, Integer> bookCounts = getBookCounts();

        // Calculate and display the total price after discount using PriceCalculator
        PriceCalculator priceCalculator = new PriceCalculator(); // Ensure proper initialization
        double totalPriceAfterDiscount = priceCalculator.calculateTotalPrice(bookCounts);
        String message = "The total price after discount is: " + totalPriceAfterDiscount + " EUR";
        logger.log(Level.INFO, message);
    }

    /**
     * Prompts the user to enter the number of copies for each book and returns a map of book titles and their counts.
     *
     * @return a map where keys are book titles and values are their respective counts
     */
    private static Map<String, Integer> getBookCounts() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> bookCounts = new HashMap<>();
        for (BookTitles bookTitle : BookTitles.values()) {
            String message = "Enter the total copies for " + bookTitle.getTitle() + ": ";
            logger.log(Level.INFO, message);
            int count = scanner.nextInt();
            bookCounts.put(bookTitle.getTitle(), count);
        }
        return bookCounts;
    }
}
