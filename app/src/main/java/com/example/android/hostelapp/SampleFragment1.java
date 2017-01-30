package com.example.android.hostelapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 24/01/2017.
 */
public class SampleFragment1 extends Fragment {
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment1, container,
                false);
        return rootView;
    }
}