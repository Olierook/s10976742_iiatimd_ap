package com.example.lckvappjudanten;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AddCamperFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_camper_view, container, false);
        return v ;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        CustomDialog cdd=new CustomDialog(this.getContext(), "Deelnemer toevegen", "Naam deelnemer", "Startbedrag");
        cdd.show();


    }
}