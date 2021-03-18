package com.example.lckvappjudanten;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class CamperOverviewActivity extends AppCompatActivity {
    static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camper_overview);
        populateStore(this);
    }

    public static void populateStore(Context c) {
        ApiRequester apiRequester = ApiRequester.getInstance(c.getApplicationContext());
        int uid = apiRequester.getUserId();
//        Boolean loggedIn = apiRequester.isLoggedIn();
//        int uid = 1;
//        if (loggedIn) {
//            uid = apiRequester.getUserId();
//        }
        AppDatabase db = AppDatabase.getInstance(c.getApplicationContext());
        Camper[] campers = db.camperDao().findByUserId(uid);
        FragmentTransaction ft = ((CamperOverviewActivity) c).getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < campers.length; i++){
            if (i == 0) {
                ft.replace(R.id.placeholder_container1, new CamperRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 1) {
                ft.replace(R.id.placeholder_container2, new CamperRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 2) {
                ft.replace(R.id.placeholder_container3, new CamperRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 3) {
                ft.replace(R.id.placeholde_container4, new CamperRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 4) {
                ft.replace(R.id.placeholder_container5, new CamperRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 5) {
                ft.replace(R.id.placeholder_container6, new CamperRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
        }
        for (int i = campers.length; i <= 6 ; i++) {
            if (i == 0) {
                ft.replace(R.id.placeholder_container1, new AddCamperFragment());
            }
            if (i == 1) {
                ft.replace(R.id.placeholder_container2, new AddCamperFragment());
            }
            if (i == 2) {
                ft.replace(R.id.placeholder_container3, new AddCamperFragment());
            }
            if (i == 3) {
                ft.replace(R.id.placeholde_container4, new AddCamperFragment());
            }
            if (i == 4) {
                ft.replace(R.id.placeholder_container5, new AddCamperFragment());
            }
            if (i == 5) {
                ft.replace(R.id.placeholder_container6, new AddCamperFragment());
            }
        }

        ft.commit();
    }

//    @Override
//    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
//        TextView store_name = (TextView) getView().findViewById(R.id.store_name);
//        TextView store_balance = (TextView) getView().findViewById(R.id.store_balance);
//        store_name.setText(this.name);
//        NumberFormat df = DecimalFormat.getInstance();
//        df.setMinimumFractionDigits(2);
//        df.setMaximumFractionDigits(4);
//        df.setRoundingMode(RoundingMode.DOWN);
//        String new_balance = "€" + df.format(this.balance);
//        store_balance.setText(new_balance);
//    }
}
