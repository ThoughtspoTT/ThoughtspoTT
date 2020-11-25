package com.thoughtspott.app;


import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class Profile_fragment1 extends Fragment{
    @Nullable







    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){


        View view =inflater.inflate(R.layout.fragment1_layout,container,false);


        //set student name
        TextView student_name = view.findViewById(R.id.textviewstudentname);
        student_name.setText("Dummy Text");


        //set student major

        TextView student_major = view.findViewById(R.id.textviewstudentmajor);
        student_major.setText("Dummy Text");

    //return inflater.inflate(R.layout.fragment1_layout,container,false);
        return view;
    }



}
