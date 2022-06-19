package ru.startandroid.p0081_viewbyid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.mytext) ;
        myTextView.setText("New text in Textview");

        Button myBtn = (Button) findViewById(R.id.myBtn) ;
        myBtn.setText("My Button");
        myBtn.setEnabled(false);

        CheckBox myChb = (CheckBox) findViewById(R.id.myChb) ;
        myChb.setChecked(true);
    }
}