package ru.startandroid.p0271_getintentaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent() ;

        String action = intent.getAction() ;

        String format = "", textInfo = "" ;

        if(action.equals("ru.startandroid.intent.action.showtime")){
            format = "HH:mm:ss" ;
            textInfo = "Time: " ;
        }
        else if(action.equals("ru.startandroid.intent.action.showdate")) {
            format = "dd.MM.yyyy" ;
            textInfo = "Date: " ;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        String datetime = sdf.format(new Date(System.currentTimeMillis())) ;

        TextView tvInfo = (TextView) findViewById(R.id.tvInfo) ;
        tvInfo.setText(textInfo+datetime);
    }
}