package com.thoughtspott.app;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.thoughtspott.app.MainActivity.user;


public class Profile_fragment1 extends Fragment{
    @Nullable







    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){


        View view =inflater.inflate(R.layout.fragment1_layout,container,false);


        //set student name
        TextView student_name = view.findViewById(R.id.textviewstudentname);
        student_name.setText(user.getNameFirst());


        //set student major

        TextView student_major = view.findViewById(R.id.description_edit_text);
        student_major.setText(user.getMajor());

    //return inflater.inflate(R.layout.fragment1_layout,container,false);
        return view;
    }



}
