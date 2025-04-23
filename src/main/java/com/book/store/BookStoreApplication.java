package com.book.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * BookStoreApplication is the main class for the book store application.
 * It allows users to select books and calculates the total price after applying discounts.
 */
public class BookStoreApplication {
    /**
     * Main method to run the book store application.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the book store!");

        // Display available books
        System.out.println("Available books with base price: 50 EUR");
        for (BookTitles bookTitle : BookTitles.values()) {
            System.out.println("- " + bookTitle.getTitle());
        }

        // Accept user input for book counts
        Map<String, Integer> bookCounts = getBookCounts();

        // Calculate and display the total price after discount using PriceCalculator
        PriceCalculator priceCalculator = new PriceCalculator(); // Ensure proper initialization
        double totalPriceAfterDiscount = priceCalculator.calculateTotalPrice(bookCounts);
        System.out.println("The total price after discount is: " + totalPriceAfterDiscount + " EUR");
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
            System.out.print("Enter the number of copies for \"" + bookTitle + "\": ");
            int count = scanner.nextInt();
            bookCounts.put(bookTitle.getTitle(), count);
        }
        return bookCounts;
    }
}
