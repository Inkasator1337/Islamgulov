package com.example.islamgulov;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class LoadedUserDataActivity extends AppCompatActivity {
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaded_user_data);
        Init();
    }
    private void Init() {
        mPlayer=MediaPlayer.create(this,R.raw.Салам);
        //устанавливаем громкость
        mPlayer.setVolume(0.2f,0.2f);
        mPlayer.setLooping(true);
        mPlayer.start();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mPlayer!=null) {
            mPlayer.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }