package com.bignerdranch.android.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
	private MediaPlayer mPLayer;
	private boolean isPause = false;
	
	public void stop() {
		if (mPLayer != null) {
			mPLayer.release();								//Releases the resource obj mPlayer is holding
			mPLayer = null;
			isPause = false;
		}
	}
	
	public void pause() {
		if (mPLayer != null) {
			mPLayer.pause();
			isPause = true;
		}
	}
	
	public void play(Context context) {
		if (!isPause) {
			stop();												//Ensures that only one instance of MediaPlayer is created
			
			mPLayer = MediaPlayer.create(context, R.raw.one_small_step);
			
			mPLayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	//As soon as the media has finished playing
				@Override
				public void onCompletion(MediaPlayer mp) {
					stop();																//Destroy the instance
				}
			});
		}
		isPause = false;
		mPLayer.start();
	}

}
