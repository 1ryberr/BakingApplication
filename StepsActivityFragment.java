package com.example.ryanberry.bakingapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ryanberry.bakingapplication.StepsAdapter.ListItemClickedListener;
import com.example.ryanberry.bakingapplication.model.Steps;
import com.example.ryanberry.bakingapplication.utilities.JsonUtils;
import java.util.ArrayList;


public class StepsActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private StepsAdapter stepsAdapter;
    private String stepArray;
    private ArrayList<Steps> steps;
    private Toolbar toolbar;
    private String recipeName;
    private CardView cardView;
    private String ingredients;
    public StepsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        stepArray = getActivity().getIntent().getExtras().getString("stepsArray");
        recipeName = getActivity().getIntent().getExtras().getString("name");
        ingredients = getActivity().getIntent().getExtras().getString("ingredients");
        steps = JsonUtils.parseStepsJson(stepArray);
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        getActivity().setTitle(recipeName);
        cardView = (CardView) rootView.findViewById(R.id.step_card_card);
        recyclerView = rootView.findViewById(R.id.step_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        stepsAdapter = new StepsAdapter(steps, new ListItemClickedListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {
                Intent intent = new Intent(getActivity(), StepsAndVideoActivity.class);
                intent.putExtra("description", steps.get(clickedItemIndex).getDescription());
                intent.putExtra("url", steps.get(clickedItemIndex).getVideoURL());
                startActivity(intent);

            System.out.println(steps.get(clickedItemIndex).getThumbnailURL());
             System.out.println(steps.get(clickedItemIndex).getVideoURL());
             System.out.println(steps.get(clickedItemIndex).getDescription());
            }
        });
        recyclerView.setAdapter(stepsAdapter);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), IngredientsActivity.class);
                intent.putExtra("ingredients", ingredients);
                startActivity(intent);

            }
        });
        return rootView;
    }
}
