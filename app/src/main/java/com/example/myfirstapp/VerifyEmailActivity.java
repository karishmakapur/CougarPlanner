package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class VerifyEmailActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    Button verifyEmailButton;
    Button verifiedEmail;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Cougar Planner - Verify Email");

        verifyEmailButton = findViewById(R.id.verifyEmailButton);
        verifiedEmail = findViewById(R.id.verifiedButton);

        verifyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Disable button
                findViewById(R.id.verifyEmailButton).setEnabled(false);

                // Send verification email
                // [START send_email_verification]
                final FirebaseUser user = mFirebaseAuth.getCurrentUser();
                user.sendEmailVerification()
                        .addOnCompleteListener(VerifyEmailActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // [START_EXCLUDE]
                                // Re-enable button
                                findViewById(R.id.verifyEmailButton).setEnabled(true);

                                if (task.isSuccessful()) {
                                    Toast.makeText(VerifyEmailActivity.this,
                                            "Verification email sent to " + user.getEmail(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("TAG", "sendEmailVerification", task.getException());
                                    Toast.makeText(VerifyEmailActivity.this,
                                            "Failed to send verification email.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                // [END_EXCLUDE]
                            }
                        });


                verifiedEmail.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mFirebaseAuth.getCurrentUser().reload().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                if (user.isEmailVerified()) {
                                    startActivity(new Intent(VerifyEmailActivity.this, FillProfileActivity.class));
                                } else {
                                    Toast.makeText(VerifyEmailActivity.this,
                                            "Email not verified. Please verify to be logged in.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });

            }
        });

    }
}
