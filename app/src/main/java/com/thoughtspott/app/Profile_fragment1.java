package com.thoughtspott.app;


import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.annotation.Nonnegative;


public class Profile_fragment1 extends Fragment{
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
      return inflater.inflate(R.layout.fragment1_layout,container,false);
    }

}
