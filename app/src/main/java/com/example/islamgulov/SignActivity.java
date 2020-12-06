package com.example.islamgulov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.islamgulov.Transform.APP_REFERENCES;
import static com.example.islamgulov.Transform.SaveUser;
import static com.example.islamgulov.Transform.StringNoNull;
import static com.example.islamgulov.Transform.Vibrate;
import static com.example.islamgulov.UserStaticInfo.AGE;
import static com.example.islamgulov.UserStaticInfo.LOGIN;
import static com.example.islamgulov.UserStaticInfo.NAME;
import static com.example.islamgulov.UserStaticInfo.PASSWORD;
import static com.example.islamgulov.UserStaticInfo.PROFILE_ID;
import static com.example.islamgulov.UserStaticInfo.STATE;
import static com.example.islamgulov.UserStaticInfo.USERS_PROFILE_INFO;
import static com.example.islamgulov.UserStaticInfo.USERS_SIGN_IN_INFO;

public class SignActivity extends AppCompatActivity {
    private EditText LoginTextView, PasswordTextView;
    private EditText NewLoginTextView,NewPasswordTextView,NewAgeTextView,NewNameTextView,NewStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Init();

        CheckSignInInfo();
    }

    private void CheckSignInInfo()
    {
        SharedPreferences sp = getSharedPreferences(APP_REFERENCES,
                Context.MODE_PRIVATE);
        String login = sp.getString(LOGIN,"");
        String password = sp.getString(PASSWORD,"");
        LoginTextView.setText(login);
        PasswordTextView.setText(password);
        SignIn();
    }

    private void Init() {
        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag21");
        // Sign in
        tabSpec.setContent(R.id.tabSignIn);
        tabSpec.setIndicator("Вход");
        //Add to tab bar
        tabHost.addTab(tabSpec);
        // Registration tab
        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tabSignUp);
        tabSpec.setIndicator("Регистрация");
        // Add to tab bar
        tabHost.addTab(tabSpec);
        // Set the first tab active
        tabHost.setCurrentTab(0);

        LoginTextView = findViewById(R.id.LoginTextView);
        PasswordTextView = findViewById(R.id.PasswordTextView);
        NewLoginTextView=findViewById(R.id.NewLoginTextView);
        NewPasswordTextView=findViewById(R.id.NewPasswordTextView);
        NewAgeTextView = findViewById(R.id.NewAgeTextView);
        NewNameTextView = findViewById(R.id.NewNameTextView);
        NewStateTextView = findViewById(R.id.NewStateTextView);
    }

    public void SignIn()
    {
       if (EditTextNoNullWithAnimation(PasswordTextView)&& EditTextNoNullWithAnimation(LoginTextView))


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
                           goNext(dataSnapshot.child(PROFILE_ID).toString(),login,getPassword());
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

    private void goNext (String profileId, String login, String password)
    {

        SaveUser(getSharedPreferences(APP_REFERENCES, Context.MODE_PRIVATE),
                login,password);
        UserStaticInfo.profileId = profileId;
        // Go to the LoadedUserDataActivity
        Intent intent = new Intent(this,LoadedUserDataActivity.class);
        startActivity(intent);
        // Close Sign in window for can't come back by user
        finish();
    }

    private void CantSignIn() {
        Toast.makeText(SignActivity.this,
                getResources().getText(R.string.CantSignInMessage),

                Toast.LENGTH_SHORT).show();
    }


    private String getPassword() {
        return  PasswordTextView.getText().toString();
    }
    private String getNewLogin() {

        return  NewLoginTextView.getText().toString();
    }
    private String getNewPassword() {
        return  NewPasswordTextView.getText().toString();
    }
    private int getNewAge() {
        try {
            return  Transform.parseIntOrDefault(NewAgeTextView.getText().toString(),0);
        }
        catch (Exception NumberFormatException)
        {
            return 0;
        }
    }

    private String getNewName() {
        return  NewNameTextView.getText().toString();
    }
    private String getNewState() {
        return NewStateTextView.getText().toString();
    }



        public void SignUp(View view) {
        if(EditTextNoNullWithAnimation(NewLoginTextView) && EditTextNoNullWithAnimation(NewPasswordTextView) && EditTextNoNullWithAnimation(NewNameTextView)&& EditTextNoNullWithAnimation(NewStateTextView))
            {
                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database1.getReference(USERS_SIGN_IN_INFO).child(getNewLogin());
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(PASSWORD).exists())
                        {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            //id for profile forming by Firebase
                            String id = database.getReference(USERS_PROFILE_INFO).push().getKey();
                            String login = getNewLogin();
                            //Adding user
                            database.getReference(USERS_SIGN_IN_INFO).child(login).child(PASSWORD).setValue(getNewPassword());
                            database.getReference(USERS_PROFILE_INFO).child(login).child(PROFILE_ID).setValue(id);
                            // Adding user profile
                            database.getReference(USERS_PROFILE_INFO).child(id).child(AGE).setValue(getNewAge());
                            database.getReference(USERS_PROFILE_INFO).child(id).child(NAME).setValue(getNewName());
                            database.getReference(USERS_PROFILE_INFO).child(id).child(STATE).setValue(getNewState());
                            goNext(id, login,getNewPassword());
                        }
                        else
                        {
                            Toast.makeText(SignActivity.this,
                                    getResources().getText(R.string.UserExistMessage),
                                    Toast.LENGTH_SHORT).show();
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

    private boolean EditTextNoNullWithAnimation(EditText animationTextView) {
        boolean NoNullText = StringNoNull(animationTextView.getText().toString());
        Animation animation = AnimationUtils.loadAnimation(
                SignActivity.this,R.anim.error_edit );
          if(!NoNullText)
              animationTextView.startAnimation(animation);
          return NoNullText;

    }
}