package ru.startandroid.p0401_layoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
    Oldingi kod:

        LayoutInflater ltInflater = getLayoutInflater() ;// getLayoutInflater usuli yordamida  LayoutInflater dan obyekt olamiz

        // View tipli view degan o'zgaruchi oldik
        // va ltInflater obyektimizning inflate usuli yordamida - text.xml dan element olyapmiz lekin qayerga yuborishni aytmadik (null)
        View view = ltInflater.inflate(R.layout.text,null,false) ;

        @SuppressLint("InflateParams")
        ViewGroup.LayoutParams lp = view.getLayoutParams(); // view ning xususiyatlarini o'ziga oladigna obyekt 'lp

        // shu qator keyin qo'shildi,ekranda view elementi ko'rsatilmagandan keyin
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout) ;// activity_main dagi linLayout id li layoutni topib oldik

        linLayout.addView(view);// va unga text.xml dan olib view ga qo'shgan elementimizni qo'shdik

        Log.d(LOG_TAG,"Class of view: "+ view.getClass().toString()) ;
        Log.d(LOG_TAG,"LayoutParams of view is null : "+ (lp == null)) ;
        Log.d(LOG_TAG,"Text of view: "+ ((TextView) view).getText()) ;

 */
        LayoutInflater ltInflater = getLayoutInflater() ;// xml fayldan element olish uchun yordam beradigan class dan obyekt olindi

        LinearLayout linLayout = findViewById(R.id.linLayout) ;

        // obyektning inflate methodi bizning view1 ga ,text.xml dan elementni olib,linLayout id li layoutga elementni yuborib beradi
        // lekin uni bizga ko'rinishi uchun, attachToRoot false bo'lmasligi kerak, agar shunday qoldirilsa u befoyda
        View view1  = ltInflater.inflate(R.layout.text,linLayout,true) ;

        // LayoutParams tipli lp1 o'zgaruvchisiga view1 ning xususiyatlari yuborildi.
        ViewGroup.LayoutParams lp1 = view1.getLayoutParams() ;

        // view1 ning classi chaqirildi
        Log.d(LOG_TAG,"Class of view1"+ view1.getClass().toString()) ;

        // yaxshi tushunmadim bi narsaga
        Log.d(LOG_TAG,"Class of layoutParams of view1"+ lp1.getClass().toString()) ;

        // View1 ning texti olinyabti
        //Log.d(LOG_TAG,"Text of view1"+ ((TextView) view1).getText()) ;

        RelativeLayout relLayout = findViewById(R.id.relLayout) ;

        View view2 = ltInflater.inflate(R.layout.text,relLayout,true) ;
        ViewGroup.LayoutParams  lp2 = view2.getLayoutParams() ;

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
        //Log.d(LOG_TAG, "Text of view2: " + ((TextView) view2).getText());
    }
}