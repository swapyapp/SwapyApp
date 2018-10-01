package com.example.muhammadgamal.swapy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    TextView signInText;
    Button signUpButton;
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    ImageView userImageSignUp;
    Uri pickedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signInText = (TextView) findViewById(R.id.signInText);
        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });

        signUpButton = (Button) findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, NavDrawerActivity.class));
            }
        });

        userImageSignUp = findViewById(R.id.userImageSignUp);
        userImageSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 22) {

                    requestPermissionAndOpenGallery();

                } else {

                    openGallery();

                }

            }
        });

    }

    private void openGallery() {

        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESTCODE);

    }

    private void requestPermissionAndOpenGallery() {

        if (ContextCompat.checkSelfPermission(SignUpActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(SignUpActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(SignUpActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            } else {

                ActivityCompat.requestPermissions(SignUpActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);

            }

        } else {

            openGallery();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null) {

            //user choose the image
            //replace the image in the UI
            pickedImageUri = data.getData();
            userImageSignUp.setImageURI(pickedImageUri);

        }
    }
}
