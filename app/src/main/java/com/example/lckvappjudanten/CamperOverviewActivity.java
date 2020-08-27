package com.example.lckvappjudanten;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class CamperOverviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camper_overview);
        populateStore();

    }

    public void populateStore() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Camper[] campers = db.camperDao().findByTentNumber(1);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
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
