package com.taan.hasani.moein.pushups;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DataBaseHandler;
import model.set;

public class pushup_activity_detail extends AppCompatActivity {

    private TextView number;
    private TextView date;
    private Button share,delete;
    private int _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushup_detail);

        number=(TextView)findViewById(R.id.nnn);
        date=(TextView)findViewById(R.id.ddd);
        share=(Button)findViewById(R.id.share);
        delete=(Button)findViewById(R.id.delete);

        set Set=(set)getIntent().getSerializableExtra("My object");

        number.setText(String.valueOf(Set.getTimes()));
        date.setText(Set.getDate());

        _id=Set.getSetId();


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler dbh=new DataBaseHandler(getApplicationContext());
                dbh.delete(_id);
                Intent i=new Intent(pushup_activity_detail.this,days_and_times.class);
                startActivity(i);
                finish();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_func();
            }


        });


    }

    private void share_func() {

        String _number=number.getText().toString();
        String _date=date.getText().toString();

        StringBuilder bulitString=new StringBuilder();

        bulitString.append("I did "+_number+" Push ups in one set !"+"\n");
        bulitString.append("On : "+_date);

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT,"My Push up New Record : \n \n "+"I did "+_number+" Push ups in one set !"+"\n");
        intent.putExtra(Intent.EXTRA_EMAIL,bulitString.toString());
        try{
            startActivity(intent.createChooser(intent,"send email "));
        }catch(ActivityNotFoundException e){
            Toast.makeText
                    (getApplicationContext(),"NO app found to send he email ",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
