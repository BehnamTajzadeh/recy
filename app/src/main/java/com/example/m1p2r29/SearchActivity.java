package com.example.m1p2r29;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m1p2r29.Models.Student;

public class SearchActivity extends AppCompatActivity {


    EditText sh;
    Button search;
    TextView name,field;
    DbHandler db;
    Student student;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        sh=findViewById(R.id.edt_search_sh);
        search=findViewById(R.id.btn_search_search);
        name=findViewById(R.id.txt_search_name);
        field=findViewById(R.id.txt_search_field);
        img=findViewById(R.id.img_search_img);
        db=new DbHandler(this);
        student=new Student();

        sh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name.setText("نام : -");
                field.setText("رشته : -");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sh.getText().toString().equals(""))
                    Toast.makeText(SearchActivity.this,R.string.validate_form,Toast.LENGTH_SHORT).show();
                db.Open();

                if(db.getCount(sh.getText().toString())==0) {
                    Toast.makeText(SearchActivity.this, R.string.notFound, Toast.LENGTH_SHORT).show();
                    name.setText("نام : -");
                    field.setText("رشته : -");
                }else {

                    student = db.search(sh.getText().toString());
                    name.setText("نام : " + student.getName());
                    field.setText("رشته : " + student.getField());

                    if (student.getPhoto() != null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(student.getPhoto(), 0, student.getPhoto().length);
                        img.setImageBitmap(bitmap);
                    }
                }
                db.close();


            }
        });

    }
}