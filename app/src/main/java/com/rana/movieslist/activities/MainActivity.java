package com.rana.movieslist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rana.movieslist.R;
import com.rana.movieslist.adapter.AdapterRVMoview;
import com.rana.movieslist.datastruct.Constants;
import com.rana.movieslist.datastruct.Movie;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewMoviesList;
    RelativeLayout relativeLayoutprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewMoviesList = (RecyclerView) findViewById(R.id.rv_movieslist);
        relativeLayoutprogress = (RelativeLayout) findViewById(R.id.relativelayoutprogress);

        populateData();
    }


    private void populateData() {

        StringRequest request = new StringRequest(
                Request.Method.GET,
                Constants.URL_BASE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            ArrayList<Movie> movieArrayList = Movie.getMovies(jsonArray);
                            AdapterRVMoview adapterRVMoview = new AdapterRVMoview(MainActivity.this, movieArrayList);
                            recyclerViewMoviesList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recyclerViewMoviesList.setAdapter(adapterRVMoview);

                            Log.e("DTA", response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error", "json parsing");
                        }
                        relativeLayoutprogress.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Error", String.valueOf(error.networkResponse.statusCode) + "");
                        relativeLayoutprogress.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error Loading", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(this).add(request);
    }
}
