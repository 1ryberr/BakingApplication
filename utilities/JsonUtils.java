package com.example.ryanberry.bakingapplication.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static JSONArray parseRecipeJson(String json) {
        JSONArray recipes = null;
        String ingredients = null;
        String name = null;
        String steps = null;
        int id = 0;

        JSONArray ingredientsArray = null;
        String measure = null;
        int quantity = 0;
        String ingredient = null;


        JSONArray stepArray = null;
        int stepID = 0;
        String shortDescription = null;
        String videoURL = null;
        String thumbnailURL = null;
        String description = null;

        try {

            recipes = new JSONArray(json);
            id = recipes.getJSONObject(1).getInt("id");
            ingredients = recipes.getJSONObject(1).getString("ingredients");
            name = recipes.getJSONObject(1).getString("name");
            steps = recipes.getJSONObject(1).getString("steps");

            stepArray = new JSONArray(steps);
            stepID = stepArray.getJSONObject(1).getInt("id");
            shortDescription = stepArray.getJSONObject(1).getString("shortDescription");
            videoURL = stepArray.getJSONObject(0).getString("videoURL");
            thumbnailURL = stepArray.getJSONObject(0).getString("thumbnailURL");
            description = stepArray.getJSONObject(0).getString("description");

            ingredientsArray = new JSONArray(ingredients);
            measure = ingredientsArray.getJSONObject(1).getString("measure");
            ingredient = ingredientsArray.getJSONObject(1).getString("ingredient");
            quantity = ingredientsArray.getJSONObject(0).getInt("quantity");


//            for (int i = 0; i < results.length(); i++) {

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TAG", "error", e);
        }


        return ingredientsArray;
    }


}
