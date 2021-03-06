package com.example.bloold.hackage.view.video.video;

import android.media.session.PlaybackState;
import android.net.Uri;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bloold.hackage.R;
import com.example.bloold.hackage.base.exoplayer.PreviewLoader;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;


public class ExoPlayerManager implements ExoPlayer.EventListener, PreviewLoader {

    // 1 minute
    private static final int ROUND_DECIMALS_THRESHOLD = 1 * 60 * 1000;

    private ExoPlayerMediaSourceBuilder mediaSourceBuilder;
    private SimpleExoPlayerView playerView;
    private SimpleExoPlayerView previewPlayerView;
    private SimpleExoPlayer player;
    private SimpleExoPlayer previewPlayer;
    private ImageButton ibSlow;
    private ImageButton ibFast;
    private ImageButton ibPause;
    private View loadingView;
    private int speed = 1;
    private float[] speeds = new float[]{0.5f, 1f, 1.5f, 2f, 4f};

    public ExoPlayerManager(SimpleExoPlayerView playerView, SimpleExoPlayerView previewPlayerView,
                            View loadingView) {
        this.playerView = playerView;
        //this.previewPlayerView = previewPlayerView;

        ibPause = (ImageButton) playerView.findViewById(R.id.exo_pause);

        this.loadingView = loadingView;
        this.mediaSourceBuilder = new ExoPlayerMediaSourceBuilder(playerView.getContext());
    }

    public void play(Uri uri) {
        mediaSourceBuilder.setUri(uri);
    }

    public void onStart() {
        if (Util.SDK_INT > 23) {
            createPlayers();
        }
    }

    public void onResume() {
        if (Util.SDK_INT <= 23) {
            createPlayers();
        }
    }

    public void onPause() {
        if (Util.SDK_INT <= 23) {
            releasePlayers();
        }
    }

    public void pausePlayer() {
        ibPause.performClick();
    }

    public void onStop() {
        if (Util.SDK_INT > 23) {
            releasePlayers();
        }
    }

    public void stopPreview() {
        player.setPlayWhenReady(true);
        //View view = previewPlayerView.getVideoSurfaceView();
        /*if (view instanceof SurfaceView) {
            view.setVisibility(View.INVISIBLE);
        }*/
    }

    private float roundOffset(float offset, int scale) {
        return (float) (Math.round(offset * Math.pow(10, scale)) / Math.pow(10, scale));
    }

    public void releasePlayers() {
        if (player != null) {
            player.release();
            player = null;
        }
        /*if (previewPlayer != null) {
            previewPlayer.release();
            previewPlayer = null;
        }*/
    }

    public void createPlayers() {
        if (player != null) {
            player.release();
        }
        /*if (previewPlayer != null) {
            previewPlayer.release();
        }*/
        player = createFullPlayer();
        playerView.setPlayer(player);
        /*previewPlayer = createPreviewPlayer();
        previewPlayerView.setPlayer(previewPlayer);*/
    }

    private SimpleExoPlayer createFullPlayer() {
        TrackSelection.Factory videoTrackSelectionFactory
                = new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter());
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();

        DefaultRenderersFactory rf = new DefaultRenderersFactory(playerView.getContext(), null, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER);
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(rf,
                trackSelector, loadControl);

        player.setPlayWhenReady(true);
        player.prepare(mediaSourceBuilder.getMediaSource(false));
        //Log.d("mediasource", "s: " + mediaSourceBuilder.getMediaSource(false).toString());
        player.addListener(this);
        return player;
    }

    private SimpleExoPlayer createPreviewPlayer() {
        TrackSelection.Factory videoTrackSelectionFactory = new WorstVideoTrackSelection.Factory();
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        LoadControl loadControl = new PreviewLoadControl();
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(playerView.getContext(),
                trackSelector, loadControl);
        player.setPlayWhenReady(false);
        player.setVolume(0f);
        player.prepare(mediaSourceBuilder.getMediaSource(true));
        return player;
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if (playbackState == PlaybackState.STATE_PLAYING) {
            loadingView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public void loadPreview(long currentPosition, long max) {
        float offset = (float) currentPosition / max;
        int scale = player.getDuration() >= ROUND_DECIMALS_THRESHOLD ? 2 : 1;
        float offsetRounded = roundOffset(offset, scale);
        player.setPlayWhenReady(false);
        previewPlayer.seekTo((long) (offsetRounded * previewPlayer.getDuration()));
        previewPlayer.setPlayWhenReady(false);
        View view = previewPlayerView.getVideoSurfaceView();
        if (view instanceof SurfaceView) {
            view.setVisibility(View.VISIBLE);
        }
    }
}
