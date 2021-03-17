package com.example.lckvappjudanten;

import android.app.Activity;
import android.content.Context;
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
    private static Context c;
    private static View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_bar_view, container, false);

    }
    public static void textSetter() {
        Button login = v.findViewById(R.id.login_button);
        if(ApiRequester.getInstance(c).isLoggedIn()) {
            login.setText("Uitloggen");
        } else {
            login.setText("Inloggen");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        v = getView();
        c = getContext();
        Button login = v.findViewById(R.id.login_button);
        textSetter();
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(ApiRequester.getInstance(getContext()).isLoggedIn()) {
            ApiRequester.getInstance(getContext()).logout();
        } else {
            LoginDialog ldd = new LoginDialog(this.getContext(), getActivity());
            ldd.show();
        }
    }

}
