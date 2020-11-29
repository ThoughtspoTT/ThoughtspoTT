package com.thoughtspott.app;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.thoughtspott.app.MainActivity.userCourses;


public class Profile_fragment2 extends Fragment{
    @Nullable



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){



        View view =inflater.inflate(R.layout.fragment2_layout,container,false);

        StringBuilder coursesFormat = new StringBuilder();
        // set classes
        TextView class_one = view.findViewById(R.id.textview_class1);
        for(String course : userCourses) coursesFormat.append(course).append('\n').append('\n');
        class_one.setText(coursesFormat);
//        TextView class_two = view.findViewById(R.id.textview_class2);
//        class_two.setText("Dummy Text");
//
//        TextView class_three = view.findViewById(R.id.textview_class3);
//        class_three.setText("Dummy Text");
//
//        TextView class_four = view.findViewById(R.id.textview_class4);
//        class_four.setText("Dummy Text");




        return view;
    }

}
