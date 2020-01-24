package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {

    private Button btn;
    private ImageView img;

    private int CAMERA = 2,GALLARY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btn = findViewById(R.id.btn_camera);
        img = findViewById(R.id.imageViewPic);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showPictureDialog();
            }
        });
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select Photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        choosePhotoFromGallery();
                        break;
                    case 1:
                        if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                            takePhotoFromCamera();
                        }else{
                            String[] permissionRequested = {Manifest.permission.CAMERA};
                            requestPermissions(permissionRequested,10);
                        }
                        takePhotoFromCamera();
                        break;
            }
        }
        });
        pictureDialog.show();

    }

    private void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLARY);

    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
           return;
        }

        if(requestCode == GALLARY){
            if(data != null){
                Uri contentURI = data.getData();
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    img.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }else if( requestCode == CAMERA){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private String saveImage(Bitmap myBitmap) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG,90, bytes);



        return "";



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 10){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePhotoFromCamera();
            }else{
                Toast.makeText(this, "Camera is not working", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
