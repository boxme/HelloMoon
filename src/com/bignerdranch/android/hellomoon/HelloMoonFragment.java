package com.bignerdranch.android.hellomoon;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class HelloMoonFragment extends Fragment {
	private AudioPlayer mPlayer = new AudioPlayer();
	private Button mPlayButton;
	private Button mPauseButton;
	private Button mStopButton;
	private VideoView videoView;
	private Uri resourceUri = Uri.parse("android.resource://" + "com.bignerdranch.android.hellomoon/raw/sample_mpeg4");
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);							//Preserve the fragment upon rotation.
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, 
			  				Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_hello_moon, parent);
		
		mPlayButton = (Button) view.findViewById(R.id.hello_moon_playButton);
		mPlayButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPlayer.play(getActivity());
			}
		});
		
		mPauseButton = (Button) view.findViewById(R.id.hello_moon_pauseButton);
		mPauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPlayer.pause();
			}
		});
		
		mStopButton = (Button) view.findViewById(R.id.hello_moon_stopButton);
		mStopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPlayer.stop();
			}
		});
		
		videoView = (VideoView) view.findViewById(R.id.video);
		MediaController controller = new MediaController(getActivity());
		controller.setAnchorView(videoView);				//Set the video view as the anchor
		controller.setMediaPlayer(videoView);				//Set a mediaplayer to play the video
		
		videoView.setMediaController(controller);
		videoView.setVideoURI(resourceUri);					//Set video path
		
		return view;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mPlayer.stop();
	}
}
