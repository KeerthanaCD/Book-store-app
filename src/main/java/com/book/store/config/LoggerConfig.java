package com.book.store.config;

import java.util.logging.*;

/**
 * LoggerConfig is a utility class that provides a method to configure and return
 * a customized {@link Logger} instance. The logger includes a custom formatter
 * for colored output based on the log level.
 */
public class LoggerConfig {

    private LoggerConfig() {
        throw new IllegalStateException("Config class");
    }

    /**
     * Configures and returns a logger instance for the given class name.
     * The logger is customized with a {@link ConsoleHandler} that applies
     * ANSI escape codes to colorize log messages based on their severity level.
     *
     * @param className the name of the class for which the logger is created
     * @return a configured {@link Logger} instance with a custom formatter
     */
    public static Logger getLogger(String className) {
        Logger logger = Logger.getLogger(className);

        // Set up custom formatter for colored output
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            /**
             * Formats the log record with ANSI color codes based on the log level.
             *
             * @param logRecord the log record to be formatted
             * @return a formatted string with colorized log message
             */
            @Override
            public synchronized String format(LogRecord logRecord) {
                String message = logRecord.getMessage();
                // Apply ANSI escape codes for color (e.g., green for INFO, red for SEVERE)
                String color = switch (logRecord.getLevel().getName()) {
                    case "INFO" -> "\u001B[32m"; // Green
                    case "WARNING" -> "\u001B[33m"; // Yellow
                    case "SEVERE" -> "\u001B[31m"; // Red
                    default -> "\u001B[0m"; // Reset
                };
                return color + message + "\u001B[0m\n"; // Reset color after message
            }
        });

        logger.setUseParentHandlers(false); // Disable default console handler
        logger.addHandler(consoleHandler);

        return logger;
    }
}
