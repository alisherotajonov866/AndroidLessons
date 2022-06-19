package ru.startandroid.p0101_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvOut ;
    Button btnOk ;
    Button btnCancel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut) ;
        btnOk = (Button) findViewById(R.id.btnOk) ;
        btnCancel = (Button) findViewById(R.id.btnCancel) ;

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             switch (view.getId()){
                 case R.id.btnOk:
                     tvOut.setText("Ok tugmasi bosildi!");
                     break;
                 case R.id.btnCancel:
                     tvOut.setText("Cancel tugmasi bosildi!");
                     break;
             }
            }
        } ;
        btnOk.setOnClickListener(oclBtn);
        btnCancel.setOnClickListener(oclBtn);
    }
}