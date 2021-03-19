package com.example.emran;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView firstGif,secondGif;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firstGif = view.findViewById(R.id.firstGifImageViewId);
        secondGif = view.findViewById(R.id.secondGifImageViewId);

        Glide.with(getContext()).load(R.raw.donate_rules).into(firstGif);
        Glide.with(getContext()).load(R.raw.blood_flow).into(secondGif);

        return view;
    }

}
