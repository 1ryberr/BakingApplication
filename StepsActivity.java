package com.example.ryanberry.bakingapplication;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class StepsActivity extends AppCompatActivity {

    TextView textView;
    private PlayerView playerView;
    private SimpleExoPlayer mExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
          textView = (TextView) findViewById(R.id.description_text1);
         playerView = (PlayerView) findViewById(R.id.playerView1);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            textView.setText("can i get some text");
            videoPlayer(" https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4");

        }
    }

    public void videoPlayer(String urlString) {
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(mExoPlayer);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "BakingApplication"));

        if (!urlString.isEmpty()) {
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(urlString));

            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }


}
