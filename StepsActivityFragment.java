package com.example.ryanberry.bakingapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private String recipeName;
    private CardView cardView;
    private String ingredients;
    private String url;
    private boolean start = false;



    public StepsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getData();
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        cardView = (CardView) rootView.findViewById(R.id.step_card_card);
        recyclerView = rootView.findViewById(R.id.step_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if(start) {


        }

        displaySetpsAndVideo();
        clickOnCard();

        return rootView;
    }

    private void displaySetpsAndVideo() {
        stepsAdapter = new StepsAdapter(steps, new ListItemClickedListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

                int orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if ((getResources().getConfiguration().screenLayout &
                            Configuration.SCREENLAYOUT_SIZE_MASK) ==
                            Configuration.SCREENLAYOUT_SIZE_LARGE) {
                         url= steps.get(clickedItemIndex).getVideoURL();
                       start = true;




                        System.out.println(url);


                        // on a large screen device ...
                        System.out.println("Landscape");
                    } else {

                        Intent intent = new Intent(getActivity(), StepsAndVideoActivity.class);
                        intent.putExtra("description", steps.get(clickedItemIndex).getDescription());
                        intent.putExtra("url", steps.get(clickedItemIndex).getVideoURL());
                        intent.putExtra("shortDescription", steps.get(clickedItemIndex).getShortDescription());
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(getActivity(), StepsAndVideoActivity.class);
                    intent.putExtra("description", steps.get(clickedItemIndex).getDescription());
                    intent.putExtra("url", steps.get(clickedItemIndex).getVideoURL());
                    intent.putExtra("shortDescription", steps.get(clickedItemIndex).getShortDescription());
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(stepsAdapter);
    }

    private void clickOnCard() {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), IngredientsActivity.class);
                intent.putExtra("ingredients", ingredients);
                startActivity(intent);

            }
        });
    }

    private void getData() {


        stepArray = getActivity().getIntent().getExtras().getString("stepsArray");
        recipeName = getActivity().getIntent().getExtras().getString("name");
        ingredients = getActivity().getIntent().getExtras().getString("ingredients");
        steps = JsonUtils.parseStepsJson(stepArray);
        getActivity().setTitle(recipeName);
    }


}
