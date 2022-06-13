package com.example.m1p2r29;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class NewStudentActivity extends AppCompatActivity {

    EditText sh,name,field;
    Button save;
    DbHandler db;
    ImageView logo,gallery,camera;
    byte[] imageInByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        sh=findViewById(R.id.edt_new_sh);
        name=findViewById(R.id.edt_new_name);
        field=findViewById(R.id.edt_new_field);
        save=findViewById(R.id.btn_new_save);
        logo=findViewById(R.id.img_new_logo);
        gallery=findViewById(R.id.img_new_gallery);
        camera=findViewById(R.id.img_new_camera);
        db=new DbHandler(this);


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,2);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.Open();

                if(name.getText().toString().equals("") || sh.getText().toString().equals("") || field.getText().toString().equals(""))
                    Toast.makeText(NewStudentActivity.this,R.string.validate_form,Toast.LENGTH_SHORT).show();
                else {
                    if(db.getCount(sh.getText().toString())>0) {
                        Toast.makeText(NewStudentActivity.this, R.string.duplicat_identifier, Toast.LENGTH_SHORT).show();
                    }else{

                        Bitmap bitmap=((BitmapDrawable)logo.getDrawable()).getBitmap();
                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                        imageInByte =stream.toByteArray();

                        db.Insert(name.getText().toString(),sh.getText().toString(),field.getText().toString(),imageInByte);


                        Toast.makeText(NewStudentActivity.this,R.string.saved,Toast.LENGTH_SHORT).show();

                        name.setText("");
                        field.setText("");
                        sh.setText("");
                        finish();
                    }
                }
                db.close();





            }
        });


        setTitle("دانشجوی جدید");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null) {
            Uri img = data.getData();
            try {
                logo.setImageURI(img);
            } catch (Exception e) {

            }
        }
        else if(requestCode==2 && resultCode==RESULT_OK && data!=null){
            Bitmap pic=(Bitmap) data.getExtras().get("data");
            logo.setImageBitmap(pic);
        }
    }
}