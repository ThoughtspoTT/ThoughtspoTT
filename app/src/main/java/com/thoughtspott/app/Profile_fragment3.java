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


public class Profile_fragment3 extends Fragment{
    @Nullable


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment3_layout,container,false);


        TextView bio_text = view.findViewById(R.id.textview_bio);
        bio_text.setText(user.getBio());

        return view;
    }

}
