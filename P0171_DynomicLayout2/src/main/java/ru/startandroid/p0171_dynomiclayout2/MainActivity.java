package ru.startandroid.p0171_dynomiclayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup rgGravity ;
    EditText etName ;
    Button btnCreate ;
    Button btnClear ;
    LinearLayout llMain ;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgGravity = (RadioGroup) findViewById(R.id.rgGravity) ;
        etName = (EditText) findViewById(R.id.etName) ;
        llMain = (LinearLayout) findViewById(R.id.llMain);

        btnCreate = (Button) findViewById(R.id.btnCreate) ;
        btnCreate.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear) ;
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnCreate:

            ViewGroup.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent,wrapContent) ;

            int btnGravity = Gravity.LEFT ; // radiobutton default holatda left da bo'lganligi uchun bu ham Left

            switch (rgGravity.getCheckedRadioButtonId()){
                case R.id.rbLeft:
                    btnGravity = Gravity.LEFT ;
                    break;
                case R.id.rbCenter:
                    btnGravity = Gravity.CENTER_HORIZONTAL ;
                    break;
                case R.id.rbRight:
                    btnGravity = Gravity.RIGHT ;
                    break;
            }
           ((LinearLayout.LayoutParams) lParams).gravity = btnGravity ;

            Button btnNew = new Button(this) ;
            btnNew.setText(etName.getText().toString());
            llMain.addView(btnNew,lParams);

            break;
            case R.id.btnClear:
                llMain.removeAllViews();
                Toast.makeText(this,"Hamma tugmalar o'chirildi!",Toast.LENGTH_SHORT).show();
                break;

        }

    }
}