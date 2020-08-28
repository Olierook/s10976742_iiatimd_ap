package com.example.lckvappjudanten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                String token = instanceIdResult.getToken();
//                Log.i("FCM Token", token);
////                saveToken(token);
//            }
//        });

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Camper[] campers = db.camperDao().findByTentNumber(1);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < campers.length; i++){
            if (i == 0) {
                ft.replace(R.id.placeholder1, new StoreRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 1) {
                ft.replace(R.id.placeholder2, new StoreRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 2) {
                ft.replace(R.id.placeholder3, new StoreRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 3) {
                ft.replace(R.id.placeholder4, new StoreRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 4) {
                ft.replace(R.id.placeholder5, new StoreRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
            if (i == 5) {
                ft.replace(R.id.placeholder6, new StoreRowFragment(campers[i].getName(), campers[i].getCurrentBalance()));
            }
        }

        ft.commit();


        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
//        ApiRequester apiRequester = new ApiRequester(getApplicationContext());
        final String URL = "https://sleepy-coast-31145.herokuapp.com/api/campers";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("hoi", response.getJSONObject(0).getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        final String url = "https://sleepy-coast-31145.herokuapp.com/api/login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
//                params.put("name", "Robbert");
                params.put("email", "robbert_olierook@hotmail.com");
                params.put("password", "hallo123");



                return params;
            }
        };
//        queue.add(postRequest);
        VolleySingleton.getInstance(this).addToQueue(postRequest);

//        final String url = "https://sleepy-coast-31145.herokuapp.com/api/campers";
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", error.toString());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("name", "Alim");
//                params.put("tentNumber", "2");
//                params.put("startingBalance", "10");
//                params.put("currentBalance", "8.3");
//                params.put("id", UUID.randomUUID().toString());
//
//
//
//                return params;
//            }
//        };
////        queue.add(postRequest);
//        VolleySingleton.getInstance(this).addToQueue(postRequest);
    }
}