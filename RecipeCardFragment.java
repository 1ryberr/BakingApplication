package com.example.ryanberry.bakingapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ryanberry.bakingapplication.model.Recipe;
import com.example.ryanberry.bakingapplication.utilities.JsonUtils;
import com.example.ryanberry.bakingapplication.utilities.NetworkUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class RecipeCardFragment extends Fragment {

    private ArrayList<Recipe> recipe = new ArrayList<>();
    private static final String TAG = "RecipeCardFragment";
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;


    public RecipeCardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {


        if (isOnline()) {
            new BakingRecipeQueryTask().execute(NetworkUtils.buildUrl());
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Network Error");
            //  builder.setMessage(R.string.error_message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    getActivity().finish();
                }
            });
            builder.show();
        }

        final View rootView = inflater.inflate(R.layout.fragment_recipe_card, container, false);
        recyclerView = rootView.findViewById(R.id.recycle_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return rootView;

    }


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Network Error");
                //   builder.setMessage(R.string.error_message);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();
                    }
                });
                builder.show();
            }
            return recipeResults;
        }

        @Override
        protected void onPostExecute(String recipeResults) {
            // mLoadingIndicator.setVisibility(View.INVISIBLE);

            recipe = JsonUtils.parseRecipeJson(recipeResults);

            if (recipeResults != null && !recipeResults.equals("")) {
                for (int i = 0; i < recipe.size(); i++) {

                    recipeAdapter = new RecipeAdapter(recipe, new RecipeAdapter.ListItemClickedListener() {
                        @Override
                        public void onListItemClick(int clickedItemIndex) {
                            String steps = recipe.get(clickedItemIndex).getSteps();
                            Intent intent = new Intent(getActivity(), StepsActivity.class);
                            intent.putExtra("stepsArray", steps);
                            startActivity(intent);
                        }
                    });

                    recyclerView.setAdapter(recipeAdapter);


                }

            }


        }

    }

}
