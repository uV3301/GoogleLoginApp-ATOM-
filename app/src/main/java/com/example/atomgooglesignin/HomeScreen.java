package com.example.atomgooglesignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity {
    ImageView ivImage;
    TextView ivName;
    Button btContinue;

    FirebaseAuth mAuth;
    GoogleSignInClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ivImage = findViewById(R.id.iv_image);
        ivName = findViewById(R.id.iv_name);
        btContinue = findViewById(R.id.bt_continue);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            Glide.with(HomeScreen.this)
                    .load(user.getPhotoUrl())
                    .into(ivImage);

            ivName.setText(user.getDisplayName());

        }

        mClient = GoogleSignIn.getClient(HomeScreen.this, GoogleSignInOptions.DEFAULT_SIGN_IN);



        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivName.getText() != null) {
                    startActivity(new Intent(HomeScreen.this, DashBoard.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                finish();
            }
        });




    }
}