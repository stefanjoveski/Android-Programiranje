package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.RecViewHolder> {

    private Context context;
    private ArrayList poll,answ1,answ2,answ3,answ4,answ5,vreme;
    private String usname;
    private Double longitude, lattitude;
    private Button buttonSubmit;




    public RecViewAdapter(Context context, ArrayList poll, ArrayList answ1, ArrayList answ2, ArrayList answ3, ArrayList answ4, ArrayList answ5, ArrayList vreme, String usname, Double longitude, Double lattitude) {
        this.context = context;
        this.poll = poll;
        this.answ1 = answ1;
        this.answ2 = answ2;
        this.answ3 = answ3;
        this.answ4 = answ4;
        this.answ5 = answ5;
        this.vreme = vreme;
        this.usname = usname;
        this.longitude = longitude;
        this.lattitude = lattitude;


    }


    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry, parent, false);
        return new RecViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        holder.poll.setText(String.valueOf(poll.get(position)));
        holder.vreme.setText(String.valueOf(vreme.get(position)));
        holder.answ1.setText(String.valueOf(answ1.get(position)));
        holder.answ2.setText(String.valueOf(answ2.get(position)));
        holder.answ3.setText(String.valueOf(answ3.get(position)));
        holder.answ4.setText(String.valueOf(answ4.get(position)));
        holder.answ5.setText(String.valueOf(answ5.get(position)));




    }

    @Override
    public int getItemCount() {
        return poll.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {
        TextView poll, vreme;
        CheckBox answ1,answ2,answ3,answ4,answ5;
        Button buttonSubmit;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            poll = itemView.findViewById(R.id.pollName);
            vreme = itemView.findViewById(R.id.vreme);
            answ1 = itemView.findViewById(R.id.answ1);
            answ2 = itemView.findViewById(R.id.answ2);
            answ3 = itemView.findViewById(R.id.answ3);
            answ4 = itemView.findViewById(R.id.answ4);
            answ5 = itemView.findViewById(R.id.answ5);
            buttonSubmit = itemView.findViewById(R.id.buttonSubmit);


        }
    }
}
