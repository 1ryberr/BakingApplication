package com.example.ryanberry.bakingapplication.utilities;

import android.util.Log;

import com.example.ryanberry.bakingapplication.Ingredients;
import com.example.ryanberry.bakingapplication.Recipe;
import com.example.ryanberry.bakingapplication.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static Recipe parseRecipeJson(String json) {
        JSONArray recipes = null;
        String ingredients = null;
        String name = null;
        String steps = null;
        int id = 0;
        Recipe myRecipe = null;

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
        ArrayList<Ingredients> ingredients1 = new ArrayList<>();
        ArrayList<Steps> steps1 = new ArrayList<>();
        try {

                recipes = new JSONArray(json);

            for (int i = 0; i < recipes.length(); i++) {

                id = recipes.getJSONObject(i).getInt("id");
                ingredients = recipes.getJSONObject(1).getString("ingredients");
                name = recipes.getJSONObject(i).getString("name");
                steps = recipes.getJSONObject(i).getString("steps");

                stepArray = new JSONArray(steps);

                for (int j = 0; j < recipes.length(); j++) {
                    stepID = stepArray.getJSONObject(j).getInt("id");
                    shortDescription = stepArray.getJSONObject(j).getString("shortDescription");
                    videoURL = stepArray.getJSONObject(j).getString("videoURL");
                    thumbnailURL = stepArray.getJSONObject(j).getString("thumbnailURL");
                    description = stepArray.getJSONObject(j).getString("description");
                    steps1.add(new Steps(stepID,shortDescription, videoURL,thumbnailURL,description));
                }
                ingredientsArray = new JSONArray(ingredients);
                for (int g = 0; g < recipes.length(); g++) {
                    measure = ingredientsArray.getJSONObject(g).getString("measure");
                    ingredient = ingredientsArray.getJSONObject(g).getString("ingredient");
                    quantity = ingredientsArray.getJSONObject(g).getInt("quantity");
                    ingredients1.add(new Ingredients(quantity,measure,ingredient));
                }
                myRecipe = new Recipe(id,name,ingredients1,steps1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TAG", "error", e);
        }


        return myRecipe;
    }


}
