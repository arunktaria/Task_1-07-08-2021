package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    ImageView imageView;
    TextView username, psswd, email;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //setting title and parent activity btn to go back
        getSupportActionBar().setTitle("Pro");
        getSupportActionBar().setHomeButtonEnabled(true);

        imageView = findViewById(R.id.userimgupload);
        username = findViewById(R.id.username_textview);
        email = findViewById(R.id.email_textview);
        psswd = findViewById(R.id.password_textview);
        btn = findViewById(R.id.btnanother);

       //getting encoded image bytes from intent
        byte[] bytesimg=getIntent().getByteArrayExtra("img");


        //setting text from intent
        username.setText(getIntent().getStringExtra("username"));
        email.setText(getIntent().getStringExtra("email"));
        psswd.setText(getIntent().getStringExtra("psswd"));
        imageView.setImageBitmap(toBitmap(bytesimg));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

    }

    //decompress btyes array to bitmap
    public Bitmap toBitmap(byte[] bytteimg)
    {
       Bitmap bitmap= BitmapFactory.decodeByteArray(bytteimg,0,bytteimg.length);
       return bitmap;
    }
}