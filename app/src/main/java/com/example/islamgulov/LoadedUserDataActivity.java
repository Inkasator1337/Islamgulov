package com.example.islamgulov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static com.example.islamgulov.UserStaticInfo.USERS_PROFILE_INFO;
import static com.example.islamgulov.UserStaticInfo.profileId;

public class LoadedUserDataActivity extends AppCompatActivity {
    FirebaseDatabase database;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaded_user_data);
        Init();

    }



     DatabaseReference myRef;
    private void Init()
    {
        mPlayer = MediaPlayer.create(this, R.raw.salam);
        //устанавливаем громкость
        mPlayer.setVolume(0.2f, 0.2f);
        mPlayer.setLooping(true);
        mPlayer.start();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
         myRef = database.getReference(USERS_PROFILE_INFO).child(profileId);
        myRef.addValueEventListener(eventListener) ;}
        ValueEventListener eventListener= new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                goNext();
            }

            private void goNext() {
                Intent intent = new Intent(LoadedUserDataActivity.this,ProfileMapsActivity.class);
                startActivity(intent);
                finish();
                myRef.removeEventListener(eventListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };



    @Override
    protected void onStart() {
        super.onStart();
        if (mPlayer != null) {
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
}