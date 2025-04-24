package com.book.store.enums;

/**
 * Enum representing book titles, authors, and publication years.
 * This enum is used to provide a structured way to manage book information.
 */
public enum BookTitles {
    CLEAN_CODE("Clean Code"),
    THE_CLEAN_CODER("The Clean Coder"),
    CLEAN_ARCHITECTURE("Clean Architecture"),
    TEST_DRIVEN_DEVELOPMENT("Test Driven Development by Example"),
    WORKING_EFFECTIVELY_WITH_LEGACY_CODE("Working Effectively With Legacy Code");

    private final String title;

    BookTitles(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
