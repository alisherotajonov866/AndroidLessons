package ru.startandroid.p0091_onccickbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener ;

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

        OnClickListener oclBtnOk = new OnClickListener() {
            @Override
            public void onClick(View v){
                tvOut.setText("OK tugmasi bosildi!");
            }
        } ;
        btnOk.setOnClickListener(oclBtnOk);

        OnClickListener oclBtnCancel = new OnClickListener() {
            @Override
            public void onClick(View v) {
            tvOut.setText("Cancel tugmasi bosildi!");
            }
        } ;
        btnCancel.setOnClickListener(oclBtnCancel);
    }
}
