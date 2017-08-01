package com.taan.hasani.moein.pushups;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import data.DataBaseHandler;
import model.set;

public class number_of_pushups extends AppCompatActivity {

    private SeekBar number_seek;
    private TextView number_text;
    private Button Submit_button;
    private DataBaseHandler dbh;
    private String number_String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_pushups);

        number_text=(TextView)findViewById(R.id.nnn);
        Submit_button=(Button)findViewById(R.id.submit);
        number_seek=(SeekBar)findViewById(R.id.seekBar);


        Submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavetoDataBase();
                finish();
                startActivity(new Intent(number_of_pushups.this,days_and_times.class));
            }


        });




        number_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                number_String=String.valueOf(progress);
                number_text.setText(number_String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void SavetoDataBase() {

        set Set=new set();

        Set.setTimes(Integer.parseInt(number_text.getText().toString()));

        dbh=new DataBaseHandler(number_of_pushups.this);

        dbh.addSet(Set);

    }
}
