package com.biplav.imagesfromgallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);
        btnSave=findViewById(R.id.btnSave);
        checkPermission();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BrowseImage();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},0
            );
        }
    }

    private void BrowseImage()
    {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (data==null)
            {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }

        Uri uri=data.getData();
        imageView.setImageURI(uri);
//        String imagePath=getRealPathFromUri(uri);
//        previewImage(imagePath);
    }

    //to show image in imageview
//    private void previewImage(String imagePath) {
//        File file=new File(imagePath);
//        if (file.exists()){
//            Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
//            imageView.setImageBitmap(bitmap);
//        }
//    }

    //to get the real path
//    private String getRealPathFromUri(Uri uri) {
//        String[] projection={MediaStore.Images.Media.DATA};
//        CursorLoader loader=new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
//        Cursor cursor=loader.loadInBackground();
//        int colIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result=cursor.getColumnName(colIndex);
//        cursor.close();
//        return result;
//    }
}
