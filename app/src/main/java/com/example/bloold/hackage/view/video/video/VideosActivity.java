package com.example.bloold.hackage.view.video.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bloold.hackage.R;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;




public class VideosActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

	private ExoPlayerManager exoPlayerManager;
	private DefaultTimeBar seekBar;
	private AudioManager audioManager;
	private FrameLayout loadingLayout;
	private ProgressBar progressBar;
	private ImageView ivBack;
	private ImageView ivCloseBanner;
	private ImageView ivBanner;

	public static Intent getIntent(Context context, String path) {
		Intent intent = new Intent(context, VideosActivity.class);
		intent.setAction(path);

		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_dictionary_videos);

		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		Intent intent = getIntent();
		String videoPath = null;
		if (intent != null) {
			videoPath = intent.getAction();
		}
		if (videoPath == null) {
			finish();
		}

		progressBar = findViewById(R.id.progressBarLoadVideo);

		SimpleExoPlayerView playerView = (SimpleExoPlayerView) findViewById(R.id.player_view);
		SimpleExoPlayerView previewPlayerView = (SimpleExoPlayerView) findViewById(R.id.previewPlayerView);

		seekBar = (DefaultTimeBar) playerView.findViewById(R.id.exo_progress);
		//seekBar.addOnSeekBarChangeListener(this);
		exoPlayerManager = new ExoPlayerManager(playerView, previewPlayerView, progressBar);

		try {
			exoPlayerManager.play(Uri.parse(videoPath));
		} catch (Exception e) {
		}

		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {

		}

		/*View view = previewPlayerView.getVideoSurfaceView();

		if (view instanceof SurfaceView) {
			SurfaceView surfaceView = (SurfaceView) view;
			//surfaceView.setZOrderMediaOverlay(true);
			//surfaceView.setZOrderOnTop(true);
			surfaceView.setVisibility(View.INVISIBLE);
		}*/

	}

	@Override
	public void onStart() {
		super.onStart();
		exoPlayerManager.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
		exoPlayerManager.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		exoPlayerManager.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
		exoPlayerManager.onStop();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		exoPlayerManager.stopPreview();
	}


	private void requestFullScreenIfLandscape() {
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_FULLSCREEN);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		{
			if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
				int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume + 1, AudioManager.FLAG_SHOW_UI);
				return true;
			}
			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
				int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume - 1, AudioManager.FLAG_SHOW_UI);
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	}

}