package com.taan.hasani.moein.pushups;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.CustomListViewAdapter;
import data.DataBaseHandler;
import model.set;

public class days_and_times extends AppCompatActivity {

    private DataBaseHandler dbh;
    private ListView listView;
    private CustomListViewAdapter customListViewAdapter;
    private ArrayList<set> setList=new ArrayList<>();
    private TextView Totalpushups,Totalsets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_and_times);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent i=new Intent(days_and_times.this,number_of_pushups.class);
                startActivity(i);
                finish();
            }
        });

        listView=(ListView)findViewById(R.id.listview);
        Totalpushups=(TextView)findViewById(R.id.totalpushups);
        Totalsets=(TextView)findViewById(R.id.totalsets);

        refreshData();


    }

    private void refreshData() {

        setList.clear();
        dbh=new DataBaseHandler(getApplicationContext());

        ArrayList<set> setsfromdb=dbh.getSets();
        int totalpushups=dbh.getTotalpushups();
        int totalsets=dbh.getTotalsets();

        Totalpushups.setText("Total push-ups : "+totalpushups);
        Totalsets.setText("Sets : "+totalsets);

        for(int i=0;i<setsfromdb.size();i++){

            String Ddate=setsfromdb.get(i).getDate();
            int Nnumbers=setsfromdb.get(i).getTimes();
            int mId=setsfromdb.get(i).getSetId();

            set nSet=new set();

            nSet.setTimes(Nnumbers);
            nSet.setSetId(mId);
            nSet.setDate(Ddate);

            setList.add(nSet);

        }
        dbh.close();

        customListViewAdapter=new CustomListViewAdapter
                (days_and_times.this,R.layout.listrow,setList);
        listView.setAdapter(customListViewAdapter);
        customListViewAdapter.notifyDataSetChanged();

    }

}
