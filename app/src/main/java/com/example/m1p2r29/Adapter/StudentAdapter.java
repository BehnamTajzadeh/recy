package com.example.m1p2r29.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m1p2r29.Models.Student;
import com.example.m1p2r29.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList){

        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.list_item_student,parent,false);


        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.name.setText(studentList.get(position).getName());
        holder.field.setText(studentList.get(position).getField());
        byte[] p=studentList.get(position).getPhoto();
        if (p != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(p, 0, p.length);
            holder.img.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView name,field;
        ImageView img;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txt_list_name);
            field=itemView.findViewById(R.id.txt_list_field);
            img=itemView.findViewById(R.id.img_list_pic);
        }
    }

}
