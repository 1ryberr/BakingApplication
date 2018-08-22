package com.example.ryanberry.bakingapplication;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.android.exoplayer2.ui.PlayerView.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepsAndVideoActivityFragment extends Fragment {
    private TextView descriptionText;
    private String stepDescription;
    private PlayerView playerView;
    private SimpleExoPlayer mExoPlayer ;
    private String  urlString;



    public StepsAndVideoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_steps_and_video, container, false);
     stepDescription = getActivity().getIntent().getExtras().getString("description");
     playerView = (PlayerView) rootView.findViewById(R.id.playerView);
        urlString = getActivity().getIntent().getExtras().getString("url");

        descriptionText = (TextView) rootView.findViewById(R.id.description_text);
       descriptionText.setText(stepDescription);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

     mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
     playerView.setPlayer(mExoPlayer);

     DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(),"BakingApplication"));

     ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(urlString));


    mExoPlayer.prepare(mediaSource);
    mExoPlayer.setPlayWhenReady(true);
    }






}
