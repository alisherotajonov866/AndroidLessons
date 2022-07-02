package ru.startandroid.p0181_dynomiclayout3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar sbWeight ;
    Button btn1 ;
    Button btn2 ;

    LinearLayout.LayoutParams  lParams1 ;
    LinearLayout.LayoutParams lParams2 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbWeight = (SeekBar) findViewById(R.id.sbWeight) ; //
        sbWeight.setOnSeekBarChangeListener(this);

        btn1 = (Button) findViewById(R.id.btn1) ;
        btn2 = (Button) findViewById(R.id.btn2) ;

        lParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams() ;
        lParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams() ;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int leftValue = progress ;
        int rightValue = seekBar.getMax()-progress ;

        lParams1.weight = leftValue;
        lParams2.weight = rightValue; // shu kod

        btn1.setText(String.valueOf(leftValue));
        btn2.setText(String.valueOf(rightValue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}