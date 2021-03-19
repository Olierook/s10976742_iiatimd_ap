package com.example.lckvappjudanten;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.ColumnInfo;

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
        Button upload = v.findViewById(R.id.upload_button);
        Button download = v.findViewById(R.id.download_button);
        if(ApiRequester.getInstance(c).getUserId() > 1) {
            login.setText("Uitloggen");
            upload.setVisibility(View.VISIBLE);
            download.setVisibility(View.VISIBLE);

        } else {
            login.setText("Inloggen");
            upload.setVisibility(View.GONE);
            download.setVisibility(View.GONE);
        }
    }

    public static void showDownloadSuccess(){
        Snackbar snackbar = Snackbar.make(v,  "Data van de server is gedownload!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showUploadSuccess(){
        Snackbar snackbar = Snackbar.make(v,  "Data is naar de server geupload!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showDownloadFail(){
        Snackbar snackbar = Snackbar.make(v,  "Downloaden is niet gelukt..", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showUploadFail(){
        Snackbar snackbar = Snackbar.make(v,  "Uploaden is niet gelukt..", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public static void showLoginFail(){
        Snackbar snackbar = Snackbar.make(v,  "Inloggen is niet gelukt..", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        v = getView();
        c = getContext();
        textSetter();
        Button login = v.findViewById(R.id.login_button);
        Button upload = v.findViewById(R.id.upload_button);
        Button download = v.findViewById(R.id.download_button);
        login.setOnClickListener(this);
        upload.setOnClickListener(this);
        download.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        ApiRequester apiRequester = ApiRequester.getInstance(getContext());
        switch (v.getId()) {
            case R.id.login_button:
                if(apiRequester.getUserId() > 1) {
                    apiRequester.logout();
                } else {
                    LoginDialog ldd = new LoginDialog(getContext(), getActivity());
                    ldd.show();
                }
                break;
            case R.id.download_button:
                apiRequester.downloadCampers();
                break;
            case R.id.upload_button:
                apiRequester.uploadCampers();
                break;
        }

    }

}
