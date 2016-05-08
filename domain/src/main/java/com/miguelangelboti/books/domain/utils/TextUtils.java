package com.miguelangelboti.books.domain.utils;

/**
 * Utility class that handles texts validation.
 * @author Miguel Ángel Botija.
 */
public class TextUtils {

    /**
     * Checks whether a String is {@code null} or empty.
     * @param string String to be checked.
     * @return {@code true} if the string is {@code null} or empty, {@code false} otherwise.
     */
    public static boolean isEmpty(String string) {
        return (string == null) || (string.length() == 0);
    }

    /**
     * Checks whether a String is not {@code null} and not empty.
     * @param string String to be checked.
     * @return {@code true} if the string is not {@code null} and not empty, {@code false} otherwise.
     */
    public static boolean isNotEmpty(String string) {
        return (string != null) && (string.length() > 0);
    }
}
