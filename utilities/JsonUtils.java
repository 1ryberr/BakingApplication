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

    public static ArrayList<Recipe> parseRecipeJson(String json) {
        ArrayList<Recipe> myRecipe = new ArrayList<>();
        JSONArray recipes = null;
        String ingredients = null;
        String name = null;
        String steps = null;
        int id = 0;

        try {

            recipes = new JSONArray(json);

            for (int i = 0; i < recipes.length(); i++) {

                id = recipes.getJSONObject(i).getInt("id");
                ingredients = recipes.getJSONObject(i).getString("ingredients");
                name = recipes.getJSONObject(i).getString("name");
                steps = recipes.getJSONObject(i).getString("steps");

                myRecipe.add(new Recipe(id,name,ingredients, steps));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TAG", "error", e);
        }

        return myRecipe;
    }

    private static ArrayList<Ingredients> parseIngredentJson(String ingredients) throws JSONException {
        ArrayList<Ingredients> ingredients1 = new ArrayList<>();
        JSONArray ingredientsArray;
        String measure;
        String ingredient;
        int quantity;
        ingredientsArray = new JSONArray(ingredients);
        for (int g = 0; g < ingredientsArray.length(); g++) {
            measure = ingredientsArray.getJSONObject(g).getString("measure");
            ingredient = ingredientsArray.getJSONObject(g).getString("ingredient");
            quantity = ingredientsArray.getJSONObject(g).getInt("quantity");
            ingredients1.add(new Ingredients(quantity, measure, ingredient));
        }
        return ingredients1;
    }


    private static ArrayList<Steps> parseStepsJson(String steps) throws JSONException {

        ArrayList<Steps> steps1 = new ArrayList<>();
        JSONArray stepArray = null;
        int stepID = 0;
        String shortDescription = null;
        String videoURL = null;
        String thumbnailURL = null;
        String description = null;


        stepArray = new JSONArray(steps);

        for (int j = 0; j < stepArray.length(); j++) {
            stepID = stepArray.getJSONObject(j).getInt("id");
            shortDescription = stepArray.getJSONObject(j).getString("shortDescription");
            videoURL = stepArray.getJSONObject(j).getString("videoURL");
            thumbnailURL = stepArray.getJSONObject(j).getString("thumbnailURL");
            description = stepArray.getJSONObject(j).getString("description");
            steps1.add(new Steps(stepID, shortDescription, description, videoURL, thumbnailURL));
        }
        return steps1;
    }



}

