package com.example.learn_api;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvAPIRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvAPIRecord = findViewById(R.id.tvAPIRecord);
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        JsonObjectRequest json = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //tvAPIRecord.setText(response.toString());
               try {
                   int userId = response.getInt("userId");
                   int id = response.getInt("id");
                   String title = response.getString("title");
                   boolean completed = response.getBoolean("completed");

                   tvAPIRecord.setText("User ID : " + userId + "\nID : " + id + "\nTITLE : " + title + "\nCompleted : " + completed);
               }catch (Exception e)
               {
                   Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue reqQ = Volley.newRequestQueue(this);
        reqQ.add(json);

    }
}