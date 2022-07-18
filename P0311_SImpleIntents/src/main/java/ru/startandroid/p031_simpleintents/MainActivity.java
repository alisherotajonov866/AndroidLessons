package ru.startandroid.p031_simpleintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnWeb ;
    Button btnMap ;
    Button btnCall ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWeb = (Button) findViewById(R.id.btnWeb) ;
        btnMap = (Button) findViewById(R.id.btnMap) ;
        btnCall = (Button) findViewById(R.id.btnCall) ;

        btnWeb.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent ;
        switch (v.getId()){
            case R.id.btnWeb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com")) ;
                startActivity(intent);
                break;
// "action.view" - biz ko'rishni hohlaymiz nimani, Uri.parse() dagi urlni va bu kod shu linkni ochishga
// yordam beradigan ilovani qidirayotganimizni anglatadi!

            case R.id.btnMap:
                intent = new Intent() ;
                intent.setAction(Intent.ACTION_VIEW) ;
                intent.setData(Uri.parse("geo:55.754283,37.62002")) ;
                startActivity(intent);
                break;
// Intent konstruktori yaratildi,lekin hech narsa yuborilmadi.
//Ushbu niyat biz xaritada ko'rsatilgan koordinatalarni ko'rib chiqmoqchi ekanligimizni anglatadi.
// "geo" - kordinatalar boshlanayotganini bildiradi

            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_DIAL) ;
                intent.setData(Uri.parse("tel:12345")) ;
                startActivity(intent);
                break;
// raqamlarni teradi lekin qo'ng'iroq qilmaydi.
// "tel" - telefon raqam boshlanayotganini bildiradi

        }

    }
}