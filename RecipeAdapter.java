package com.example.ryanberry.bakingapplication;

import com.example.ryanberry.bakingapplication.model.Recipe;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {
    private static final String TAG = "RecipeAdapter";
    final private ListItemClickedListener mOnClickListener;
    ArrayList<Recipe> recipes = new ArrayList<>();

    public interface ListItemClickedListener {
        void onListItemClick(int clickedItemIndex);
    }

    public RecipeAdapter(ArrayList<Recipe> recipes, ListItemClickedListener listener) {
        this.recipes = recipes;
        this.mOnClickListener = listener;
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_card_item, parent, false);
        RecipeAdapterViewHolder viewHolder = new RecipeAdapterViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, final int position) {
        holder.recipeLabel.setText(recipes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView recipeLabel;
        CardView cardView;

        public RecipeAdapterViewHolder(View itemView) {
            super(itemView);
            recipeLabel = (TextView) itemView.findViewById(R.id.recipe_name);
            cardView = (CardView) itemView.findViewById(R.id.card);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
          mOnClickListener.onListItemClick(clickedPosition);

        }
    }

}
