package com.example.lckvappjudanten;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    private int userId = 1;

    public ApiRequester(Context context) {
        this.context = context;
        VolleySingleton.getInstance(context).getRequestQueue();
    }

    public static ApiRequester getInstance(Context context) {
        if (apiRequester == null) {
            apiRequester = new ApiRequester(context);
        }
        apiRequester.setContext(context);
        return apiRequester;
    }

    private void setContext(Context context) {
        this.context = context;
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
    public void syncUserId(){
        AppDatabase db = AppDatabase.getInstance(context);
        if(db.activeUserDao().getAll().length > 0) {
            Log.d("before", "syncUserId: " + userId);
            setUserId(db.activeUserDao().getAll()[0].getId());
            Log.d("after", "syncUserId: " + userId);
        }
    }
    public boolean isLoggedIn(){
        if(bearerToken != null && !bearerToken.trim().isEmpty() && userId > 1) {
            return true;
        }
        return false;

    }

    private void finalizeLogin(final String userPw) {
        final String url = "https://sleepy-coast-31145.herokuapp.com/api/user";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            Log.d("finalize", "onResponse: here");
                            JSONObject jobj = new JSONObject(response);
                            setUserId(jobj.getInt("id"));
                            AppDatabase db = AppDatabase.getInstance(context);
                            if(db.activeUserDao().getAll().length > 0){
                                db.activeUserDao().delete(
                                    db.activeUserDao().getAll()[0]
                                );
                            }
                            Log.d("about to insert", "onResponse: here");

                            db.activeUserDao().insertAll(new ActiveUser(
                                    jobj.getString("name"),
                                    jobj.getString("email"),
                                    userPw,
                                    userId
                            ));
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
                            finalizeLogin(userPw);
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
                            finalizeLogin(userPw);
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
                            setUserId(1);
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





    public void addOriginCamper(Camper camper) {
        if (isLoggedIn()) {
            final String camperName = camper.getName();
            final String startingBalance = camper.getStartingBalance().toString();
            final String currentBalance = camper.getCurrentBalance().toString();
            final String id = camper.getId();

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
                    params.put("currentBalance", currentBalance);
                    params.put("user_id", String.valueOf(userId));
                    params.put("id", id);

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

    public void updateOriginCamper(Camper camper) {
        if (isLoggedIn()) {
            final String camperName = camper.getName();
            final String startingBalance = camper.getStartingBalance().toString();
            final String currentBalance = camper.getCurrentBalance().toString();
            final String id = camper.getId();

            final String url = "https://sleepy-coast-31145.herokuapp.com/api/campers" + id;
            StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
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
                    params.put("currentBalance", currentBalance);
                    params.put("user_id", String.valueOf(userId));

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

    public void removeOriginCamper(String id) {
        if (isLoggedIn()) {

            final String url = "https://sleepy-coast-31145.herokuapp.com/api/campers" + id;
            StringRequest postRequest = new StringRequest(Request.Method.DELETE, url,
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

    public void uploadCampers() {
        if (!isLoggedIn()) {
            uploadWithLogin();
        } else {
            replaceOriginCampers();
        }
    }

    public void downloadCampers() {
        if (!isLoggedIn()) {
            downloadWithLogin();
        }
        else {
            replaceLocalCampers();
        }
    }

    private void uploadWithLogin () {
        AppDatabase db = AppDatabase.getInstance(context);
        final String userEmail = db.activeUserDao().getAll()[0].getEmail();
        final String userPw = db.activeUserDao().getAll()[0].getPassword();
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
                            replaceOriginCampers();
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

    private void downloadWithLogin () {
        AppDatabase db = AppDatabase.getInstance(context);
        final String userEmail = db.activeUserDao().getAll()[0].getEmail();
        final String userPw = db.activeUserDao().getAll()[0].getPassword();
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
                            replaceLocalCampers();
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

    private void replaceOriginCampers () {
        final String url = "https://sleepy-coast-31145.herokuapp.com/api/campers/" + userId;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            AppDatabase db = AppDatabase.getInstance(context);
                            JSONArray jarr = new JSONArray(response);
                            Camper[] localCampers = db.camperDao().findByUserId(userId);
                            for (int i = 0; i < jarr.length(); i++) {
                                String id = jarr.getJSONObject(i).getString("id");
                                boolean isLocallyPresent = false;
                                for (int j = 0; j < localCampers.length; j++) {
                                    if (id == localCampers[j].getId()){
                                        isLocallyPresent = true;
                                    }
                                }
                                if (!isLocallyPresent) {
                                    removeOriginCamper(id);
                                }
//                                String name = jarr.getJSONObject(i).getString("name");
//                                int user_id = jarr.getJSONObject(i).getInt("user_id");
//                                double startingBalance = jarr.getJSONObject(i).getDouble("startingBalance");
//                                double currentBalance = jarr.getJSONObject(i).getDouble("currentBalance");
//                                Camper camper = new Camper(name, user_id, startingBalance, currentBalance);
//                                camper.setId(id);
//                                db.camperDao().insertAll(camper);
                            }
                            for (int i = 0; i < localCampers.length; i++) {
                                String id = localCampers[i].getId();
                                boolean isPresentInOrigin = false;
                                for (int j = 0; j < jarr.length(); j++) {
                                    if (id == jarr.getJSONObject(j).getString("id")) {
                                        isPresentInOrigin = true;
                                        updateOriginCamper(localCampers[i]);
                                    }
                                }
                                if (!isPresentInOrigin) {
                                    addOriginCamper(localCampers[i]);
                                }
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void replaceLocalCampers () {
        final String url = "https://sleepy-coast-31145.herokuapp.com/api/campers/" + userId;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            JSONArray jarr = new JSONArray(response);
                            AppDatabase db = AppDatabase.getInstance(context);
                            db.camperDao().deleteAll(userId);
                            for (int i = 0; i < jarr.length(); i++) {
                                String id = jarr.getJSONObject(i).getString("id");
                                String name = jarr.getJSONObject(i).getString("name");
                                int user_id = jarr.getJSONObject(i).getInt("user_id");
                                double startingBalance = jarr.getJSONObject(i).getDouble("startingBalance");
                                double currentBalance = jarr.getJSONObject(i).getDouble("currentBalance");
                                Camper camper = new Camper(name, user_id, startingBalance, currentBalance);
                                camper.setId(id);
                                db.camperDao().insertAll(camper);
                            }
                            if(MainActivity.active) {
                                MainActivity.populateStore(context);
                            }
                            if(CamperOverviewActivity.active) {
                                CamperOverviewActivity.populateStore(context);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
