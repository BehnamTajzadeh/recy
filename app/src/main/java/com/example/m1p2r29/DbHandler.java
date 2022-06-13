package com.example.m1p2r29;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.m1p2r29.Models.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static String DBPATH="";
    private static String DBNAME="student.db";
    Context context;

    public DbHandler(@Nullable Context context) {
        super(context, "book", null, 1);
        this.context=context;
        DBPATH=context.getCacheDir().getPath()+"/"+DBNAME;
    }

    public boolean DbExist(){
        File file=new File(DBPATH);
        if (file.exists()){
            return true;
        }else
            return false;
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public void Insert(String name,String sh,String field,byte[] photo){
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("identifier",sh);
        cv.put("field",field);
        cv.put("photo",photo);
        db.insert("tbl_student","name",cv);
    }

    public List<Student> StudentList(){
        List<Student> studentList =new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from tbl_student",null);
        cursor.moveToFirst();
        do{
            Student student=new Student(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getBlob(4));
            studentList.add(student);
        }while(cursor.moveToNext());


        return studentList;
    }

    public Student search(String identifier){

        Cursor cursor=db.rawQuery("select * from tbl_student where identifier ='"+identifier+"'",null);
        cursor.moveToFirst();
        Student student=new Student(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getBlob(4));
        return student;
    }

    public int getCount(String identifier){
        Cursor cursor=db.rawQuery("select * from tbl_student where identifier ='"+identifier+"'",null);
        return cursor.getCount();
    }


    public String Show(){
        Cursor cursor=db.rawQuery("select name from tbl_student",null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public boolean CopyDb(){
        try{
            FileOutputStream out=new FileOutputStream(DBPATH);
            InputStream in=context.getAssets().open(DBNAME);
            byte[] buffer = new byte[1024];

            int ch;
            while((ch=in.read(buffer))>0){
                out.write(buffer,0,ch);
            }
            out.flush();
            out.close();
            in.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void Open(){
        if(DbExist()){
            db=SQLiteDatabase.openDatabase(DBPATH,null,SQLiteDatabase.OPEN_READWRITE);
        }else{
            if(CopyDb())
                Open();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
