package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2;


    private String API_URL = "https://jsonplaceholder.typicode.com/posts/1" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=findViewById(R.id.text);
        tv2=findViewById(R.id.textview);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    tv1.setText(response.get("title").toString());
                    tv2.setText(response.get("body").toString());
                }catch (JSONException e){
                    throw new RuntimeException(e);
                }

//                Toast.makeText(MainActivity.this, "Success :" +response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error Here"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}