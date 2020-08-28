package com.example.lckvappjudanten;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ActionBar extends Fragment
        implements View.OnClickListener
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.action_bar_view, container, false);
        return v ;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button login = view.findViewById(R.id.login_button);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        CustomDialog cdd=new CustomDialog(this.getContext(), (CamperOverviewActivity) getActivity(), "Inloggen", "Gebruikersnaam", "Wachtwoord");
        cdd.show();


    }

}
