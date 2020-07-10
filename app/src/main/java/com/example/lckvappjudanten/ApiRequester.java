//package com.example.lckvappjudanten;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//public class ApiRequester {
////    private Context context;
////
////    public ApiRequester(Context context) {
////        this.context = context;
////    }
////
////    RequestQueue queue = VolleySingleton.getInstance(this.context).getRequestQueue();
//
//    final String URL = "http://10.0.2.2:8000/api/campers";
//    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
//            (Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
//
//                @Override
//                public void onResponse(JSONArray response) {
//                    try {
//                        Log.d("hoi", response.getJSONObject(0).getString("name"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // TODO: Handle error
//
//                }
//            });
//        VolleySingleton.getInstance(this).addToQueue(jsonObjectRequest);
////        try {
////            RequestQueue requestQueue = Volley.newRequestQueue(this);
////            String URL = "http://10.0.2.2:8000/api/register";
////            JSONObject jsonBody = new JSONObject();
////            jsonBody.put("name", "henk");
////            jsonBody.put("email", "henk@toptal.com");
////            jsonBody.put("password", "henkhenk9191");
////            jsonBody.put("password_confirmation", "henkhenk9191");
////
////
////            final String requestBody = jsonBody.toString();
////
////            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
////                @Override
////                public void onResponse(String response) {
////                    Log.d("VOLLEY1", response);
////                }
////            }, new Response.ErrorListener() {
////                @Override
////                public void onErrorResponse(VolleyError error) {
////                    Log.d("VOLLEY2", error.toString());
////                }
////            }) {
////                @Override
////                public String getBodyContentType() {
////                    return "application/json; charset=utf-8";
////                }
////
////                @Override
////                public byte[] getBody() throws AuthFailureError {
////                    try {
////                        return requestBody == null ? null : requestBody.getBytes("utf-8");
////                    } catch (UnsupportedEncodingException uee) {
////                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
////                        return null;
////                    }
////                }
////
////                @Override
////                protected Response<String> parseNetworkResponse(NetworkResponse response) {
////                    String responseString = "";
////                    if (response != null) {
////                        responseString = String.valueOf(response.statusCode);
////                        // can get more details such as response.headers
////                    }
////                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
////                }
////            };
////            stringRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
////            requestQueue.add(stringRequest);
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//}
