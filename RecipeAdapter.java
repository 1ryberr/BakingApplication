package com.example.ryanberry.bakingapplication;
import com.example.ryanberry.bakingapplication.model.Recipe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {
    private static final String TAG = "RecipeAdapter";
    ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }


    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_car_item, parent, false);
        RecipeAdapterViewHolder viewHolder = new RecipeAdapterViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {

        holder.recipeLabel.setText(recipes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView recipeLabel;

        public RecipeAdapterViewHolder(View itemView) {
            super(itemView);
            recipeLabel = (TextView) itemView.findViewById(R.id.recipe_name);
        }


    }

}
