package com.example.ryanberry.bakingapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanberry.bakingapplication.StepsAdapter.ListItemClickedListener;
import com.example.ryanberry.bakingapplication.model.Steps;
import com.example.ryanberry.bakingapplication.utilities.JsonUtils;

import java.util.ArrayList;

import static android.content.Intent.getIntent;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepsActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private StepsAdapter stepsAdapter;
    private String stepArray;
    private ArrayList<Steps> steps;





    public StepsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        stepArray = getActivity().getIntent().getExtras().getString("stepsArray");
        steps = JsonUtils.parseStepsJson(stepArray);
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        recyclerView = rootView.findViewById(R.id.step_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        stepsAdapter = new StepsAdapter(steps, new ListItemClickedListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

            }
        });
        recyclerView.setAdapter(stepsAdapter);

        return rootView;
    }
}
