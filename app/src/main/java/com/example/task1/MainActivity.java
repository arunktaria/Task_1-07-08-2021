package com.example.task1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageButton captureimg;
    EditText username, email, password;
    Bitmap bitmap;
    ConstraintLayout layout;
    byte[] byttg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find all items id's from xml layout file
        Button submitbtn = findViewById(R.id.signupbtn);
        username = findViewById(R.id.usernamesign);
        email = findViewById(R.id.emailsign);
        password = findViewById(R.id.passwordsign);
        captureimg = findViewById(R.id.captureimg);
        layout = findViewById(R.id.constraint_layout);

        //assign defualt image to bitmap (for preventing crashes)
        bitmap = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.userimg1);
        toByte(bitmap);

        captureimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamtxt, psswd, emailtxt;
                usernamtxt = username.getText().toString();
                psswd = password.getText().toString();
                emailtxt = email.getText().toString();
                //created intent object and assigned data to send to next activity
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("username", usernamtxt);
                intent.putExtra("email", emailtxt);
                intent.putExtra("psswd", psswd);
                intent.putExtra("img", byttg);
                startActivity(intent);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {

            if (requestCode == 100) {
                //assigned clicked image to bitmap
                bitmap = (Bitmap) data.getExtras().get("data");
                captureimg.setImageBitmap(bitmap);
                toByte(bitmap);
            }
        } else {
            Snackbar.make(this, layout, "no image selected!", Snackbar.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //converting bitmap to bytes array (for sending it to next activity)
    public byte[] toByte(Bitmap bitmap1) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byttg = byteArrayOutputStream.toByteArray();
        return byttg;
    }




}