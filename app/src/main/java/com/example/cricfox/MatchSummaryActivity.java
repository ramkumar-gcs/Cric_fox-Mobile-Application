package com.example.cricfox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MatchSummaryActivity extends AppCompatActivity {

    String url="https://cricapi.com/api/fantasySummary?apikey=3lBMsgFX3JMPgoNqEiCE4hgJUvD2&unique_id=";

    TextView field1TitleTv,field1DetailTv,field2TitleTv,field2DetailTv,
            bat1TitleTv,bat1DetailTv,bat2TitleTv,bat2DetailTv,
             bowl1TitleTv,bowl1DetailTv,bowl2TitleTv,bowl2DetailTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_summary);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Match Summary");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent =getIntent();
        String uniqueId=intent.getStringExtra("match_id");
        url=url+uniqueId;

        bat1TitleTv=findViewById(R.id.bat1TitleTv);
        bat1DetailTv=findViewById(R.id.bat1DetailTv);
        bat2TitleTv=findViewById(R.id.bat2TitleTv);
        bat2DetailTv=findViewById(R.id.bat2DetailTv);

        bowl1TitleTv=findViewById(R.id.bowl1TitleTv);
        bowl1DetailTv=findViewById(R.id.bowl1DetailTv);
        bowl2TitleTv=findViewById(R.id.bowl2TitleTv);
        bowl2DetailTv=findViewById(R.id.bowl2DetailTv);

        field1TitleTv=findViewById(R.id.field1TitleTv);
        field1DetailTv=findViewById(R.id.field1DetailTv);
        field2TitleTv=findViewById(R.id.field2TitleTv);
        field2DetailTv=findViewById(R.id.field2DetailTv);

        loadData();
    }
    private void loadData(){
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        try{
                            JSONObject jsonObject =new JSONObject(response);
                            JSONObject dataJObject =jsonObject.getJSONObject("data");

                            JSONArray fieldArray = dataJObject.getJSONArray("fielding");
                            JSONArray bowlArray = dataJObject.getJSONArray("bowling");
                            JSONArray batArray = dataJObject.getJSONArray("batting");

                            JSONObject field0=fieldArray.getJSONObject(0);
                            JSONObject field1=fieldArray.getJSONObject(1);


                            String field1Title =field0.getString("title");
                            String field2Title =field1.getString("title");

                            JSONArray field1ScoresArray = field0.getJSONArray("scores");
                            JSONArray field2ScoresArray = field1.getJSONArray("scores");

                            field1TitleTv.setText(field1Title);
                            for(int i=0;i<field1ScoresArray.length();i++){

                                String name=field1ScoresArray.getJSONObject(i).getString("name");
                                String bowled=field1ScoresArray.getJSONObject(i).getString("bowled");
                                String catche=field1ScoresArray.getJSONObject(i).getString("catch");
                                String lbw=field1ScoresArray.getJSONObject(i).getString("lbw");
                                String runout=field1ScoresArray.getJSONObject(i).getString("runout");
                                String stumped=field1ScoresArray.getJSONObject(i).getString("stumped");

                                field1DetailTv.append("Name: "+ name
                                        +"\nBowled: "+ bowled
                                        +"\nCatch "+ catche
                                        +"\nLBW "+ lbw
                                        +"\nRun Out"+ runout
                                        +"\nStumped "+stumped +"\n\n"
                                );
                            }
                            field2TitleTv.setText(field2Title);
                            for(int i=0;i<field2ScoresArray.length();i++){

                                String name=field2ScoresArray.getJSONObject(i).getString("name");
                                String bowled=field2ScoresArray.getJSONObject(i).getString("bowled");
                                String catche=field2ScoresArray.getJSONObject(i).getString("catch");
                                String lbw=field2ScoresArray.getJSONObject(i).getString("lbw");
                                String runout=field2ScoresArray.getJSONObject(i).getString("runout");
                                String stumped=field2ScoresArray.getJSONObject(i).getString("stumped");

                                field2DetailTv.append("Name: "+ name
                                        +"\nBowled: "+ bowled
                                        +"\nCatch "+ catche
                                        +"\nLBW "+ lbw
                                        +"\nRun Out"+ runout
                                        +"\nStumped "+stumped +"\n\n"
                                );
                            }

                            JSONObject bowl0=bowlArray.getJSONObject(0);
                            JSONObject bowl1=bowlArray.getJSONObject(1);

                            String bowl1Title =bowl0.getString("title");
                            String bowl2Title =bowl1.getString("title");

                            JSONArray bowl1ScoresArray = bowl0.getJSONArray("scores");
                            JSONArray bowl2ScoresArray = bowl1.getJSONArray("scores");

                            bowl1TitleTv.setText(bowl1Title);
                            for(int i=0;i<bowl1ScoresArray.length();i++){
                                String bowlerName = bowl1ScoresArray.getJSONObject(i).getString("bowler");
                                String overs = bowl1ScoresArray.getJSONObject(i).getString("O");
                                String wickets = bowl1ScoresArray.getJSONObject(i).getString("W");
                                String runs = bowl1ScoresArray.getJSONObject(i).getString("R");
                                String dots = bowl1ScoresArray.getJSONObject(i).getString("0s");
                                String fours = bowl1ScoresArray.getJSONObject(i).getString("4s");
                                String sixes = bowl1ScoresArray.getJSONObject(i).getString("6s");
                                String maiden = bowl1ScoresArray.getJSONObject(i).getString("M");
                                String econ = bowl1ScoresArray.getJSONObject(i).getString("Econ");

                                bowl1DetailTv.append("Name :" + bowlerName
                                        +"\nOvers :" + overs
                                        +"\nWickets :" + wickets
                                        +"\nRuns :" + runs
                                        +"\nDots :" + dots
                                        +"\n4's :"+ fours
                                        +"\n6's "+ sixes
                                        +"\nMaiden :" +maiden
                                        +"\nEconomy :" + econ
                                        +"\n\n"
                                );
                            }

                            bowl2TitleTv.setText(bowl2Title);
                            for(int i=0;i<bowl2ScoresArray.length();i++){
                                String bowlerName = bowl2ScoresArray.getJSONObject(i).getString("bowler");
                                String overs = bowl2ScoresArray.getJSONObject(i).getString("O");
                                String wickets = bowl2ScoresArray.getJSONObject(i).getString("W");
                                String runs = bowl2ScoresArray.getJSONObject(i).getString("R");
                                String dots = bowl2ScoresArray.getJSONObject(i).getString("0s");
                                String fours = bowl2ScoresArray.getJSONObject(i).getString("4s");
                                String sixes = bowl2ScoresArray.getJSONObject(i).getString("6s");
                                String maiden = bowl2ScoresArray.getJSONObject(i).getString("M");
                                String econ = bowl2ScoresArray.getJSONObject(i).getString("Econ");

                                bowl2DetailTv.append("Name :" + bowlerName
                                        +"\nOvers :" + overs
                                        +"\nWickets :" + wickets
                                        +"\nRuns :" + runs
                                        +"\nDots :" + dots
                                        +"\n4's :"+ fours
                                        +"\n6's "+ sixes
                                        +"\nMaiden :" +maiden
                                        +"\nEconomy :" + econ
                                        +"\n\n"
                                );
                            }

                            JSONObject bat0=batArray.getJSONObject(0);
                            JSONObject bat1=batArray.getJSONObject(1);


                            String bat1Title =bat0.getString("title");
                            String bat2Title =bat1.getString("title");

                            JSONArray bat1ScoresArray = bat0.getJSONArray("scores");
                            JSONArray bat2ScoresArray = bat1.getJSONArray("scores");

                            bat1TitleTv.setText(bat1Title);
                            for(int i=0;i<bat1ScoresArray.length();i++){
                                String batsman = bat1ScoresArray.getJSONObject(i).getString("batsman");
                                String balls = bat1ScoresArray.getJSONObject(i).getString("B");
                                String runs = bat1ScoresArray.getJSONObject(i).getString("R");
                                String fours = bat1ScoresArray.getJSONObject(i).getString("4s");
                                String sixes = bat1ScoresArray.getJSONObject(i).getString("6s");
                                String StrikeRate = bat1ScoresArray.getJSONObject(i).getString("SR");
                                String dismissalInfo = bat1ScoresArray.getJSONObject(i).getString("dismissal-info");
                                String dismissal ="" , dismissedby ="" ;
                                try{
                                    dismissal=bat1ScoresArray.getJSONObject(i).getString("dismissal");
                                    dismissedby=bat1ScoresArray.getJSONObject(i).getJSONObject("dismissed-by").getString("name");
                                }
                                catch (Exception e){
                                    //Toast.makeText(MatchSummaryActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }

                                bat1DetailTv.append("Name :" + batsman
                                        +"\nBalls :" + balls
                                        +"\nRuns :" + runs
                                        +"\n4's :"+ fours
                                        +"\n6's "+ sixes
                                        +"\nSR:" +StrikeRate
                                        +"\nDismissal :" + dismissal
                                        +"\nDismissal-Info :"+dismissalInfo
                                        +"\nDismissed-by :"+ dismissedby
                                        +"\n\n"
                                );
                            }
                            bat2TitleTv.setText(bat2Title);
                            for(int i=0;i<bat2ScoresArray.length();i++){
                                String batsman = bat2ScoresArray.getJSONObject(i).getString("batsman");
                                String balls = bat2ScoresArray.getJSONObject(i).getString("B");
                                String runs = bat2ScoresArray.getJSONObject(i).getString("R");
                                String fours = bat2ScoresArray.getJSONObject(i).getString("4s");
                                String sixes = bat2ScoresArray.getJSONObject(i).getString("6s");
                                String StrikeRate = bat2ScoresArray.getJSONObject(i).getString("SR");
                                String dismissalInfo = bat2ScoresArray.getJSONObject(i).getString("dismissal-info");
                                String dismissal ="" , dismissedby ="" ;
                                try{
                                    dismissal=bat2ScoresArray.getJSONObject(i).getString("dismissal");
                                    dismissedby=bat2ScoresArray.getJSONObject(i).getJSONObject("dismissed-by").getString("name");
                                }
                                catch (Exception e){
                                    //Toast.makeText(MatchSummaryActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }

                                bat2DetailTv.append("Name :" + batsman
                                        +"\nBalls :" + balls
                                        +"\nRuns :" + runs
                                        +"\n4's :"+ fours
                                        +"\n6's "+ sixes
                                        +"\nSR:" +StrikeRate
                                        +"\nDismissal :" + dismissal
                                        +"\nDismissal-Info :"+dismissalInfo
                                        +"\nDismissed-by :"+ dismissedby
                                        +"\n\n"
                                );
                            }



                        }
                        catch (Exception e){
                            Toast.makeText(MatchSummaryActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchSummaryActivity.this,"Error: "+error.toString(),Toast.LENGTH_SHORT).show();
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
