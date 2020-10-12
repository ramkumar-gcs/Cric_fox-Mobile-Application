package com.example.cricfox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder>{

    private List<Model> modelList;
    private Context context;

    public MyAdapter(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final Model model=modelList.get(position);
        holder.team1Tv.setText(model.getTeam1());
        holder.team2Tv.setText(model.getTeam2());
        holder.matchStatusTv.setText(model.getMatchStatus());
        holder.matchTypeTv.setText(model.getMatchType());
        holder.dataTv.setText(model.getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String matchId=model.getId();
                final String date=model.getDate();

                String[] options={"Match Detail" , "Playing X1" , "Match Summary"};
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle("Choose option");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0)
                        {
                            Intent intent=new Intent(context,MatchDetailActivity.class);
                            intent.putExtra("match_id",matchId);
                            intent.putExtra("date",date);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                        if(i==1)
                        {
                            Intent intent=new Intent(context,PlayersActivity.class);
                            intent.putExtra("match_id",matchId);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                        if(i==2)
                        {
                            Intent intent=new Intent(context,MatchSummaryActivity.class);
                            intent.putExtra("match_id",matchId);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                });
                builder.create().show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView team1Tv,team2Tv,matchTypeTv,matchStatusTv,dataTv;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            team1Tv=itemView.findViewById(R.id.team1Tv);
            team2Tv=itemView.findViewById(R.id.team2Tv);
            matchTypeTv=itemView.findViewById(R.id.matchTypeTv);
            matchStatusTv=itemView.findViewById(R.id.matchStatusTv);
            dataTv=itemView.findViewById(R.id.dateTv);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
