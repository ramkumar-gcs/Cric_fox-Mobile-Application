package com.example.cricfox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlayersActivity extends AppCompatActivity {

    TextView team1NameTv,team2NameTv,team1PlayersTv,team2PlayersTV;
    String url="https://cricapi.com/api/fantasySquad/?apikey=kgGqUMU2xRZaZSK8VdHleXGNOmB3&unique_id=";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Players");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent =getIntent();
        String uniqueId=intent.getStringExtra("match_id");
        url=url+uniqueId;
        team1NameTv=findViewById(R.id.team1NameTv);
        team2NameTv=findViewById(R.id.team2NameTv);
        team1PlayersTv=findViewById(R.id.team1PlayersTv);
        team2PlayersTV=findViewById(R.id.team2PlayersTv);

        loadData();
    }
    private void loadData(){
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        //team1NameTv.setText(url);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //team1NameTv.setText(url);
                try {

                    JSONArray squadArray=new JSONObject(response).getJSONArray("squad");
                    JSONObject json0 = squadArray.getJSONObject(0);
                    JSONObject json1 = squadArray.getJSONObject(1);

                    String team1Name=json0.getString("name");
                    String team2Name=json1.getString("name");

                    JSONArray team1Array = json0.getJSONArray("players");
                    JSONArray team2Array = json1.getJSONArray("players");

                    team1NameTv.setText(team1Name);
                    team2NameTv.setText(team2Name);

                    for (int i=0;i<team1Array.length();i++){
                        String team1=team1Array.getJSONObject(i).getString("name");
                        team1PlayersTv.append((i+1)+") "+team1 +"\n");
                    }
                    for (int i=0;i<team2Array.length();i++){
                        String team2=team2Array.getJSONObject(i).getString("name");
                        team2PlayersTV.append((i+1)+") "+team2 +"\n");
                    }
                }
                catch (Exception e){
                    Toast.makeText(PlayersActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PlayersActivity.this,"Error: "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
