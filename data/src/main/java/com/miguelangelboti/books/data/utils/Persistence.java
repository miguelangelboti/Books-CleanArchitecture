package com.miguelangelboti.books.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miguelangelboti.books.domain.entities.Book;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This utility class allows persist data in internal storage.
 * @author Miguel Ángel Botija.
 */
public class Persistence {

    /**
     * The favorites store key.
     */
    private static final String KEY_FAVORITES = "KEY_FAVORITES";

    ///////////////////////////////////////////////////////////////////////////
    // Remote manager methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Store the favorites.
     * @param context The context.
     * @param books The list of favorites.
     * @return {@code true} if data is stored correctly, {@code false} otherwise.
     */
    public static boolean store(Context context, List<Book> books) {
        String jsonString = Utils.toJson(books);
        return Utils.saveValue(context, KEY_FAVORITES, jsonString);
    }

    /**
     * Load the list of favorites.
     * @param context The context.
     * @return The favorites.
     */
    public static List<Book> loadFavorites(Context context) {

        String jsonString = Utils.loadValue(context, KEY_FAVORITES);
        if (jsonString != null) {
            return Utils.fromJson(jsonString, new TypeToken<List<Book>>() {
            }.getType());
        }

        // No favorites stored, empty list will be returned.
        return new ArrayList<>();
    }

    /////////////////////////////////////////////////////////////////////////////
    // Utils for persistence
    /////////////////////////////////////////////////////////////////////////////

    /**
     * This inner class contains the logic to store and load data to and from internal storage.
     */
    private static class Utils {

        private static final String TAG = "Persistence";

        private static final String STORE_NAME = "com.miguelangelboti.books";

        /**
         * The gson parser.
         */
        private static final Gson gson = new Gson();

        /**
         * Serializes the specified object into its equivalent Json representation.
         * @param object The object.
         * @return return A Json representation of {@code src}. Returns {@code null} if {@code object} is {@code null}.
         */
        private static String toJson(Object object) {
            return (object != null) ? gson.toJson(object) : null;
        }

        /**
         * Deserializes the specified Json into an object of the specified class.
         * @param <T> The type parameter.
         * @param json The string from which the object is to be deserialized.
         * @param classOfT The class of T.
         * @return An object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
         */
        private static <T> T fromJson(String json, Class<T> classOfT) {
            // TODO: Check possible exception!
            return gson.fromJson(json, classOfT);
        }

        /**
         * Deserializes the specified Json into an object of the specified class.
         * @param <T> The type parameter.
         * @param json The string from which the object is to be deserialized.
         * @param typeOfT The type of T.
         * @return An object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
         */
        private static <T> T fromJson(String json, Type typeOfT) {
            // TODO: Check possible exceptions!
            return gson.fromJson(json, typeOfT);
        }

        /**
         * Load a text from preferences.
         * @param context The context.
         * @param key The key to load.
         * @return The value.
         */
        private static String loadValue(Context context, String key) {
            return loadValue(context, key, null);
        }

        /**
         * Load a value from preferences.
         * @param context The context.
         * @param key The key to load.
         * @param defaultValue The default value if key has not value saved.
         * @return The value.
         */
        private static String loadValue(Context context, String key, String defaultValue) {

            String value;

            try {
                SharedPreferences preferences = context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
                value = preferences.getString(key, defaultValue);
            } catch (Exception exception) {
                value = null;
            }

            return (value != null) ? value : defaultValue;
        }

        /**
         * Save a value.
         * @param context The context.
         * @param key The key to save.
         * @param value The value to save.
         * @return {@code true} if success, {@code false} otherwise.
         */
        private static boolean saveValue(Context context, String key, String value) {

            try {
                SharedPreferences preferences = context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
                Editor editor = preferences.edit();
                editor.putString(key, value);
                return editor.commit();
            } catch (Exception exception) {
                Log.w(TAG, "There was a problem saving the data.");
            }

            return false;
        }
    }
}
