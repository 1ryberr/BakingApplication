package com.example.ryanberry.bakingapplication;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class StepsAndVideoActivityFragment extends Fragment {
    private TextView descriptionText;
    private String stepDescription;
    private PlayerView playerView;
    private SimpleExoPlayer mExoPlayer;
    private String urlString;
    private String shortDescription;


    public StepsAndVideoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        stepDescription = getActivity().getIntent().getExtras().getString("description");
        urlString = getActivity().getIntent().getExtras().getString("url");
        shortDescription = getActivity().getIntent().getExtras().getString("shortDescription");
        getActivity().setTitle(shortDescription);

        final View rootView = inflater.inflate(R.layout.fragment_steps_and_video, container, false);
        playerView = (PlayerView) rootView.findViewById(R.id.playerView);
        descriptionText = (TextView) rootView.findViewById(R.id.description_text);
        descriptionText.setText(stepDescription);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
        playerView.setPlayer(mExoPlayer);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "BakingApplication"));

        if (!urlString.isEmpty()) {
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(urlString));

            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        mExoPlayer.release();
        mExoPlayer = null;
    }
}
