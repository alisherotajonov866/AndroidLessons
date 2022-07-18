package ru.startandroid.p0301_activityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRed;
    Button btnGreen;
    Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btnRed = (Button) findViewById(R.id.btnRed);
        btnGreen = (Button) findViewById(R.id.btnGreen);
        btnBlue = (Button) findViewById(R.id.btnBlue);

        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent() ; // malumot yaratiladigan sinfda Intentdan meros olinadi!
        switch (v.getId()){
            case R.id.btnRed:
                intent.putExtra("Color", Color.RED) ;
                break;
            case R.id.btnGreen:
                intent.putExtra("Color", Color.GREEN) ;
                break;
            case R.id.btnBlue:
                intent.putExtra("Color", Color.BLUE) ;
                break;
        }
        setResult(RESULT_OK,intent);
        finish();

    }
}