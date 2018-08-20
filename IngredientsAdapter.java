package com.example.ryanberry.bakingapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryanberry.bakingapplication.model.Ingredients;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsAdapterViewHolder>   {

    private static final String TAG = "IngredientsAdapter";

    ArrayList<Ingredients> ingredients = new ArrayList<>();



    public IngredientsAdapter(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public IngredientsAdapter.IngredientsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredient_card_item, parent, false);
        IngredientsAdapter.IngredientsAdapterViewHolder  viewHolder = new IngredientsAdapter.IngredientsAdapterViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(IngredientsAdapter.IngredientsAdapterViewHolder  holder, final int position) {
        holder.ingredientLabel.setText(ingredients.get(position).getIngredient());
        holder.quantityLabel.setText( String.valueOf(ingredients.get(position).getQuantity()));
        holder.measure.setText(ingredients.get(position).getMeasure());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientsAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientLabel;
        TextView quantityLabel;
        TextView measure;
        CardView cardView;

        public IngredientsAdapterViewHolder(View itemView) {
            super(itemView);
            ingredientLabel = (TextView) itemView.findViewById(R.id.ingredients_card_name);
            quantityLabel = (TextView) itemView.findViewById(R.id.quantity);
            measure = (TextView) itemView.findViewById(R.id.measure);
            cardView = (CardView) itemView.findViewById(R.id.card);
        }

    }

}
