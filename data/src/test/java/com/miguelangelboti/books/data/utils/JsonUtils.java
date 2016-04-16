package com.miguelangelboti.books.data.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that allows converting a JSON to a String for tests.
 * @author Miguel Ángel Botija.
 */
public final class JsonUtils {

    private static final String JSON_PATH = "./src/test/resources/json";

    private JsonUtils() {
    }

    /**
     * Returns the contents of the file.
     * @param jsonFileName file name in the JSON_PATH folder.
     * @return The contests of the file as a String.
     */
    public static String getStringFromFile(String jsonFileName) {
        return getStringFromFile(new File(JSON_PATH, jsonFileName));
    }

    private static String getStringFromFile(File jsonFile) {
        try {
            if (jsonFile == null) {
                return "";
            }
            return new Scanner(jsonFile, "UTF-8").useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
