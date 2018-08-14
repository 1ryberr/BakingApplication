package com.example.ryanberry.bakingapplication;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ryanberry.bakingapplication.utilities.JsonUtils;
import com.example.ryanberry.bakingapplication.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new BakingRecipeQueryTask().execute(NetworkUtils.buildUrl());

    }

    public class BakingRecipeQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL recipeURL = params[0];
            String recipeResults = null;

            try {
                recipeResults = NetworkUtils.getResponseFromHttpUrl(recipeURL);

            } catch (IOException e) {
                e.printStackTrace();
                //  mLoadingIndicator.setVisibility(View.INVISIBLE);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Network Error");
                //   builder.setMessage(R.string.error_message);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
                builder.show();
            }
            return recipeResults;
        }

        @Override
        protected void onPostExecute(String recipeResults) {
            // mLoadingIndicator.setVisibility(View.INVISIBLE);

          JSONArray recipe = JsonUtils.parseRecipeJson(recipeResults);

            if (recipeResults != null && !recipeResults.equals("")) {
//                for (int i = 0; i < recipe.size(); i++) {
//                    recipe.get(1).getName();
//                    recipe.get(1).getId();
//                    Log.v(TAG, recipe.get(1).getName());
//                    Log.v(TAG, String.valueOf(recipe.get(1).getId()));
//                    for (int j = 0; j < recipe.get(1).getSteps().size(); j++) {
//                        Log.v(TAG, recipe.get(1).getSteps().get(j).getDescription().toString());
//
//                        System.out.println(recipe.get(1).getSteps().get(1).getDescription());
//                    }
//                }


                try {

                    for (int j = 0; j < recipe.length(); j++) {
                        System.out.println(recipe.get(j));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }

    }


}

