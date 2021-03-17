package com.payal.picsumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemRecyclerAdapter itemRecyclerAdapter;
    ArrayList<OneItem> itemArrayList  = new ArrayList<>();
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "https://picsum.photos/list";
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        itemRecyclerAdapter = new ItemRecyclerAdapter(MainActivity.this,itemArrayList);
        recyclerView.setAdapter(itemRecyclerAdapter);
        prepare_list();
    }

    private void prepare_list() {

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseresponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Sorry try again later", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);

    }

    private void parseresponse(JSONArray response) {

        try {
            for(int i=0;i<response.length();++i){
                JSONObject data = (JSONObject) response.get(i);
                String title = data.getString("author");
                String imgurl = "https://picsum.photos/300/300?image="+data.getString("id");
                OneItem item = new OneItem();
                item.setTitle(title);
                item.setImage(imgurl);
                itemArrayList.add(item);
            }
            itemRecyclerAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("jsonerror", "parseresponse: "+e);
        }
    }
}