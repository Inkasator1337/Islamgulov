package com.example.islamgulov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.islamgulov.Transform.StringNoNull;
import static com.example.islamgulov.Transform.Vibrate;
import static com.example.islamgulov.UserStaticInfo.PASSWORD;
import static com.example.islamgulov.UserStaticInfo.PROFILE_ID;
import static com.example.islamgulov.UserStaticInfo.USERS_SIGN_IN_INFO;

public class SignActivity extends AppCompatActivity {
    private EditText LoginTextView, PasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Init();
    }

    private void Init() {
        LoginTextView = findViewById(R.id.LoginTextView);
        PasswordTextView = findViewById(R.id.PasswordTextView);
    }

    public void SignIn(View view) {
       if (StringNoNull(getPassword())&& StringNoNull(getLogin()))


       {
           FirebaseDatabase database = FirebaseDatabase.getInstance();
           DatabaseReference myRef = database.getReference(USERS_SIGN_IN_INFO);
           final ValueEventListener valueEventListener = myRef.addValueEventListener(new ValueEventListener()
           {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot)
               {
                   String login = getLogin();
                   Object value = dataSnapshot.child(login).child(PASSWORD).getValue();

                   if (value != null)
                   {
                       if (value.toString().equals(getPassword())) {
                           goNext(dataSnapshot.child(login).child(PROFILE_ID).getValue().toString());
                       } else CantSignIn();
                   }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });






       }
       else
       {
           Vibrate(SignActivity.this);
           Toast.makeText(SignActivity.this,
                   getResources().getText(R.string.NullParametersMessage),
                   Toast.LENGTH_SHORT).show();
       }
    }

    private String getLogin()
    {
        return LoginTextView.getText().toString();
    }

    private void goNext(String profileId) {
        UserStaticInfo.profileId = profileId;
    }

    private void CantSignIn() {
        Toast.makeText(SignActivity.this,
                getResources().getText(R.string.CantSignInMessage),

                Toast.LENGTH_SHORT).show();
    }


    private String getPassword() {
        return  PasswordTextView.getText().toString();
    }


}