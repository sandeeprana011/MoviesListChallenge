package com.rana.movieslist.datastruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sandeeprana on 15/10/16.
 * License is only applicable to individuals and non-profits
 * and that any for-profit company must
 * purchase a different license, and create
 * a second commercial license of your
 * choosing for companies
 */

public class Movie {
    private String title;
    private String imageUrl;
    private int rating;
    private int releaseYear;
    private ArrayList<String> genre;

    public Movie(JSONObject object) {
        this.title = getString(object, JKeys.TITLE, "No Title");
        this.imageUrl = getString(object, JKeys.IMAGE, "No Title");
        this.rating = getInt(object, JKeys.RATING, -1);
        this.releaseYear = getInt(object, JKeys.RELEASE_YEAR, -1);
        this.genre = getStringArraylistFromJSONObject(object, JKeys.GENRE);
    }

    public Movie(String title, String imageUrl, int rating, int releaseYear, ArrayList<String> genre) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public static ArrayList<Movie> getMovies(JSONArray jsonArray) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    movieArrayList.add(new Movie(jsonArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return movieArrayList;
        } else {
            return null;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public boolean isOk(JSONObject object, String keyName) {
        return object.has(keyName) && !object.isNull(keyName);
    }

    private String getString(JSONObject object, String keyName, String defaultValue) {
        if (isOk(object, keyName)) {
            try {
                return object.getString(keyName);
            } catch (JSONException e) {
                e.printStackTrace();
                return defaultValue;
            }
        } else
            return defaultValue;
    }

    private int getInt(JSONObject object, String keyName, int defaultValue) {
        if (isOk(object, keyName)) {
            try {
                return object.getInt(keyName);
            } catch (JSONException e) {
                e.printStackTrace();
                return defaultValue;
            }
        } else
            return defaultValue;
    }

    private ArrayList<String> getStringArraylistFromJSONObject(JSONObject object, String keyName) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (isOk(object, keyName)) {
            try {
                JSONArray array = object.getJSONArray(keyName);
                for (int i = 0; i < array.length(); i++) {
                    arrayList.add(array.getString(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public String getFormattedGenre() {
        String toFormat = "";
        if (this.getGenre() != null) {
            for (String gen : getGenre()) {
                toFormat = toFormat + gen + " | ";
            }
        }
        return toFormat;
    }

    public String getFormattedYear() {
        if (this.getReleaseYear() != -1) {
            return "Year : " + String.valueOf(getReleaseYear());
        } else return "Year : n/a";
    }

}
