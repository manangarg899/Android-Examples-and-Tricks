package com.example.nikhil.exoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Create a default TrackSelector
        Handler handler = new Handler();
        BandwidthMeter meter = new DefaultBandwidthMeter();
        TrackSelection.Factory selector = new AdaptiveTrackSelection.Factory(meter);
        TrackSelector trackSelector = new DefaultTrackSelector(selector);

        // 2. Create a player
        player = ExoPlayerFactory.newSimpleInstance(this,trackSelector);
        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.simple_exoplayerview);
        exoPlayerView.setPlayer(player);

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory datasourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,"ExoPlayer"),bandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        Uri uri = Uri.parse("file://"+ Environment.getExternalStorageDirectory().getPath()+"/Video/Ember Island - Creep.mp4");
        MediaSource mediaSource = new ExtractorMediaSource(uri,datasourceFactory,extractorsFactory,null,null);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onBackPressed() {
        player.release();
    }
}
