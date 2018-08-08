package com.example.ryanberry.bakingapplication;

import java.util.ArrayList;

public class Recipe {

    private int id;
    private String name;
    private ArrayList<Ingredients> ingredients;
    private ArrayList<Steps> steps;

    public Recipe(int id, String name, ArrayList<Ingredients>  ingredients, ArrayList<Steps> steps ) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }


    public int getId() {
        
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredients>  getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients>  ingredients) {
        this.ingredients = ingredients;
    }
}


