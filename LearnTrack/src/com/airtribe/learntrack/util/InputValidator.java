package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public final class InputValidator {
    private InputValidator() {
    }

    public static int parsePositiveInt(String value, String errorMessage) {
        try {
            int number = Integer.parseInt(value);
            if (number <= 0) throw new InvalidInputException(errorMessage);
            return number;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(errorMessage);
        }
    }

    public static String requireNonBlank(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(errorMessage);
        }
        return value.trim();
    }
}
