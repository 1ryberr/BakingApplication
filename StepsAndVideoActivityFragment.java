package com.example.ryanberry.bakingapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.PlayerView;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepsAndVideoActivityFragment extends Fragment {
    private TextView descriptionText;
    private String stepDescription;
    private PlayerView playerView;





    public StepsAndVideoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_steps_and_video, container, false);
     stepDescription = getActivity().getIntent().getExtras().getString("description");
     playerView = (PlayerView) rootView.findViewById(R.id.playerView);

       descriptionText =(TextView) rootView.findViewById(R.id.description_text);
       descriptionText.setText(stepDescription);
        return rootView;
    }
}
