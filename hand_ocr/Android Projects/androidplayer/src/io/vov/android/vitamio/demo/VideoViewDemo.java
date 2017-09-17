package io.vov.android.vitamio.demo;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.MediaPlayer.OnSubtitleUpdateListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;


public class VideoViewDemo extends Activity {
	private String path = "/sdcard/myavi.3gp";
	private VideoView mVideoView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	mVideoView = (VideoView) findViewById(R.id.surface_view);
		mVideoView.setVideoPath(path);
		mVideoView.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mVideoView.setSubPath("/sdcard/myavi.3gp");
				mVideoView.setSubShown(true);
			}
		});
		mVideoView.setOnSubtitleUpdateListener(new OnSubtitleUpdateListener() {
			@Override
			public void onSubtitleUpdate(String arg0) {
				Log.i("VitamioDemo", arg0);
			}

			@Override
			public void onSubtitleUpdate(byte[] arg0, int arg1, int arg2) {
			}
		});
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.requestFocus();
	}
    private int mLayout = VideoView.VIDEO_LAYOUT_ZOOM;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (mVideoView != null)
			mVideoView.setVideoLayout(mLayout, 0);
		super.onConfigurationChanged(newConfig);
	}
}
