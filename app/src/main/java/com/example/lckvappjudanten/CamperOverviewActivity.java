package com.example.lckvappjudanten;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class CamperOverviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camper_overview);
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Camper[] campers = db.camperDao().findByTentNumber(1);
        Log.d("tag2", "onCreate: " + campers);

//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        int i = ((FrameLayout) v.getParent()).getId();
//        ft.replace(i, new StoreRowFragment("Anton", 19.85));
//        Log.d("tag", "onClick: " +i);
//        ft.commit();
    }
}
