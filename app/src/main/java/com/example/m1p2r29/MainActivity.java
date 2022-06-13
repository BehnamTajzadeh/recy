package com.example.m1p2r29;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.m1p2r29.Adapter.StudentAdapter;

public class MainActivity extends AppCompatActivity {


    Button insert,search;
    RecyclerView recyclerView;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DbHandler(this);

        insert=findViewById(R.id.btn_main_new);
        search=findViewById(R.id.btn_main_search);

        recyclerView=findViewById(R.id.rec);

        showStudents();


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewStudentActivity.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showStudents();
    }

    private void showStudents(){
        db.Open();
        StudentAdapter studentAdapter=new StudentAdapter(this,db.StudentList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(studentAdapter);
        db.close();
    }
}