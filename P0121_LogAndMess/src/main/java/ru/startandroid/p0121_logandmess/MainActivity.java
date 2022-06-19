package ru.startandroid.p0121_logandmess;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    private static final String TAG  = "myLogs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"найдем View-элементы") ;
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Log.d(TAG,"присваиваем обработчик кнопкам") ;
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"по id определяем кнопку, вызвавшую этот обработчик") ;
        switch (v.getId()) {
            case R.id.btnOk:
                // кнопка ОК
                Log.d(TAG,"OK tugmasi bosildi.") ;
                tvOut.setText("OK tugmasi bosildi!");
                Toast.makeText(this,"Ok tugmasi bosildi",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCancel:
                // кнопка Cancel
                Log.d(TAG,"Cancel tugmasi bosildi.") ;
                tvOut.setText("Cancel tugmasi bosildi!");
                Toast.makeText(this,"CANCEL tugmasi bosildi",Toast.LENGTH_LONG).show();
                break;
        }
    }
}