package com.example.ryanberry.bakingapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ryanberry.bakingapplication.model.Steps;
import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder> {

    private static final String TAG = "StepsAdapter";
    final private ListItemClickedListener mOnClickListener;
    ArrayList<Steps> steps = new ArrayList<>();

    public interface ListItemClickedListener {
        void onListItemClick(int clickedItemIndex);
    }

    public StepsAdapter(ArrayList<Steps> steps, ListItemClickedListener listener) {
        this.steps =steps;
        this.mOnClickListener = listener;
    }

    @Override
    public StepsAdapter.StepsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.step_card_item, parent, false);
        StepsAdapter.StepsAdapterViewHolder viewHolder = new StepsAdapter.StepsAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapterViewHolder holder, int position) {
        holder.recipeLabel.setText(steps.get(position).getShortDescription());
    }


    @Override
    public int getItemCount() {
        return steps.size();
    }

    class StepsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView recipeLabel;
        CardView cardView;

        public StepsAdapterViewHolder(View itemView) {
            super(itemView);
            recipeLabel = (TextView) itemView.findViewById(R.id.step_name);
            cardView = (CardView) itemView.findViewById(R.id.step_card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);

        }
    }




}
