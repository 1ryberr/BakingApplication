package com.example.ryanberry.bakingapplication;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ryanberry.bakingapplication.utilities.JsonUtils;
import com.example.ryanberry.bakingapplication.utilities.NetworkUtils;
import java.io.IOException;
import java.net.URL;



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

            if ( recipeResults != null && ! recipeResults.equals("")) {
               Log.v(TAG,JsonUtils.parseRecipeJson(recipeResults).toString());

            }


        }

    }



}

