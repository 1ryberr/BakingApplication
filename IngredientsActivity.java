package com.example.ryanberry.bakingapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ryanberry.bakingapplication.model.Ingredients;
import com.example.ryanberry.bakingapplication.utilities.JsonUtils;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String ingredients;
    private ArrayList<Ingredients> listIngredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ingredients = getIntent().getExtras().getString("ingredients");

        setContentView(R.layout.activity_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.ingredients_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        listIngredients = JsonUtils.parseIngredentJson(ingredients);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(listIngredients);
        recyclerView.setAdapter(ingredientsAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
