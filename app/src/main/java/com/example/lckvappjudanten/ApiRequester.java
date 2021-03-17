package com.example.lckvappjudanten;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ApiRequester {
    private static ApiRequester apiRequester = null;
    private String bearerToken = null;
    private Context context;
    private int userId = 0;

    public ApiRequester(Context context) {
        this.context = context;
        VolleySingleton.getInstance(context).getRequestQueue();
    }

    public static ApiRequester getInstance(Context context) {
        if (apiRequester == null) {
            apiRequester = new ApiRequester(context);
        }
        return apiRequester;
    }


    private void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }
    private void setUserId(int userId) {
        this.userId = userId;
        ActionBar.textSetter();
    }
    public int getUserId() {
        return userId;
    }
    public boolean isLoggedIn(){
        if(bearerToken != null && !bearerToken.trim().isEmpty() && userId > 0) {
            return true;
        }
        return false;

    }

    private void obtainUserId() {
        final String url = "https://sleepy-coast-31145.herokuapp.com/api/user";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject jobj = new JSONObject(response);
                            setUserId(jobj.getInt("id"));
                            Log.d("credentials", "onResponse: " + userId + " " + bearerToken );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", bearerToken);
                return params;
            }

        };
        VolleySingleton.getInstance(context).addToQueue(postRequest);
    }

    public void register(String name, String email, String password) {
        final String userName = name;
        final String userEmail = email;
        final String userPw = password;
        final String url = "https://sleepy-coast-31145.herokuapp.com/api/register";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject jobj = new JSONObject(response);
                            setBearerToken("Bearer " + jobj.getString("access_token"));
                            obtainUserId();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                params.put("name", userName);
                params.put("email", userEmail);
                params.put("password", userPw);

                return params;
            }

        };
        VolleySingleton.getInstance(context).addToQueue(postRequest);
    }

    public void login(String email, String password) {
        final String userEmail = email;
        final String userPw = password;
        final String url = "https://sleepy-coast-31145.herokuapp.com/api/login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject jobj = new JSONObject(response);
                            setBearerToken("Bearer " + jobj.getString("access_token"));
                            obtainUserId();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
//                params.put("name", userName);
                params.put("email", userEmail);
                params.put("password", userPw);

                return params;
            }

        };
        VolleySingleton.getInstance(context).addToQueue(postRequest);
    }

    public void logout() {
        if (isLoggedIn()) {
            final String url = "https://sleepy-coast-31145.herokuapp.com/api/logout";
            StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            // response
                            setBearerToken(null);
                            setUserId(0);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", bearerToken);
                    return params;
                }
            };
            VolleySingleton.getInstance(context).addToQueue(postRequest);
        }
    }





    public void addCamper(String name, Double balance) {
        if (isLoggedIn()) {
            final String camperName = name;
            final String startingBalance = balance.toString();
            final String url = "https://sleepy-coast-31145.herokuapp.com/api/campers";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", camperName);
                    params.put("startingBalance", startingBalance);
                    params.put("currentBalance", startingBalance);
                    params.put("user_id", String.valueOf(userId));
                    params.put("id", UUID.randomUUID().toString());

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", bearerToken);
                    return params;
                }
            };
            VolleySingleton.getInstance(context).addToQueue(postRequest);
        }
    }

    public void getCampers() {
        if (isLoggedIn()) {
            final String url = "https://sleepy-coast-31145.herokuapp.com/api/campers";
            StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", bearerToken);
                    return params;
                }
            };
            VolleySingleton.getInstance(context).addToQueue(postRequest);
        }
    }

}
