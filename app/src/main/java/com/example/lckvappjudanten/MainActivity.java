package com.example.lckvappjudanten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private String selectedCamperId;
    private Double selectedProductPrice;
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

    public void setSelectedCamperId(String selectedCamperId) {
        this.selectedCamperId = selectedCamperId;
    }

    public void setSelectedProductPrice(Double selectedProductPrice) {
        this.selectedProductPrice = selectedProductPrice;
    }

    public void makeSale() {
        ApiRequester apiRequester = ApiRequester.getInstance(getApplicationContext());
        int uid = apiRequester.getUserId();

//        Boolean loggedIn = apiRequester.isLoggedIn();
//        int uid = 1;
//        if (loggedIn) {
//            uid = apiRequester.getUserId();
//        }
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Camper camper = db.camperDao().getCamper(selectedCamperId)[0];
        Double newBalance = camper.getCurrentBalance() - selectedProductPrice;
        db.camperDao().makeSale(newBalance, selectedCamperId);
        populateStore(this);
        Log.d("sale", "makeSale: " + selectedCamperId + " " + selectedProductPrice);
    }

    public static void populateStore (Context c) {
        ApiRequester apiRequester = ApiRequester.getInstance(c.getApplicationContext());
        int uid = apiRequester.getUserId();

//        Boolean loggedIn = apiRequester.isLoggedIn();
//        int uid = 1;
//        if (loggedIn) {
//            uid = apiRequester.getUserId();
//        }
        AppDatabase db = AppDatabase.getInstance(c.getApplicationContext());
        Camper[] campers = db.camperDao().findByUserId(uid);
        FragmentTransaction ft = ((MainActivity) c).getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < campers.length; i++) {
            StoreRowFragment storeRowFragment = new StoreRowFragment(
                    (MainActivity) c,
                    campers[i].getName(),
                    campers[i].getCurrentBalance(),
                    campers[i].getId()
            );
            switch (i) {
                case 0:
                    ft.replace(R.id.placeholder1, storeRowFragment);
                    break;
                case 1:
                    ft.replace(R.id.placeholder2, storeRowFragment);
                    break;
                case 2:
                    ft.replace(R.id.placeholder3, storeRowFragment);
                    break;
                case 3:
                    ft.replace(R.id.placeholder4, storeRowFragment);
                    break;
                case 4:
                    ft.replace(R.id.placeholder5, storeRowFragment);
                    break;
                case 5:
                    ft.replace(R.id.placeholder6, storeRowFragment);
                    break;
            }
        }
        for (int i = campers.length; i <= 6; i++) {
            EmptyStoreRowFragment emptyStoreRowFragment = new EmptyStoreRowFragment();
            switch (i) {
                case 0:
                    ft.replace(R.id.placeholder1, emptyStoreRowFragment);
                    break;
                case 1:
                    ft.replace(R.id.placeholder2, emptyStoreRowFragment);
                    break;
                case 2:
                    ft.replace(R.id.placeholder3, emptyStoreRowFragment);
                    break;
                case 3:
                    ft.replace(R.id.placeholder4, emptyStoreRowFragment);
                    break;
                case 4:
                    ft.replace(R.id.placeholder5, emptyStoreRowFragment);
                    break;
                case 5:
                    ft.replace(R.id.placeholder6, emptyStoreRowFragment);
                    break;
            }
        }
        ft.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiRequester.getInstance(getApplicationContext()).syncUserId();
        populateStore(this);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.i("FCM Token", token);
//                saveToken(token);
            }
        });
        }


}