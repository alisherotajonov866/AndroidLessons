package ru.startandroid.p0361_sqlitequery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    String name[] = {"Xitoy","AQSH","Braziliya","Rossiya","Yaponiya","Germaniya","Misr","Italiya","Fransiya","Kanada"} ;
    int people[] = {1400, 311, 195, 142, 128, 82, 80, 60, 66, 35} ;
    String region[] = {"Osiya","Amerika","Amerika","Yevropa","Osiya","Yevropa","Afrika",
    "Yevropa","Yevropa","Amerika"} ;

    Button btnAll, btnFunc, btnPeople, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    DBHelper dbHelper ; // DBHelper classi bilan bizni bog'lovchi,shu class tipli global o'zgaruvchi
    SQLiteDatabase db ; // bazaga yangi narsa qo'shmoqchi bo'lsak ,bazaning onCreate usuli bilan bizni bog'lovchi vosita

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);

        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);

        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this) ; // DBHelper classining obyektini e'lon qildik.

        db = dbHelper.getWritableDatabase() ; // bazaga ulanish
        // so'rov yubordik
        Cursor c = db.query("mytable",null,null,null,null,null,null) ;

        if (c.getCount()==0){ // baza teshkirildi // bazadan bizga malumot olib keladigan "quti"
            ContentValues cv = new ContentValues() ;// bazaga bizning kodimizni olib boradigan "quti"
            for (int i=0;i<10;i++){ // bo'sh bazaga malumot yozilyapti
                cv.put("name",name[i]);
                cv.put("people",people[i]);
                cv.put("region",region[i]);
                Log.d(LOG_TAG, "id = " + db.insert("mytable", null, cv));
            }
        }
        c.close(); // soro'v yuborish to'xtatildi
        dbHelper.close(); // bazaga ulanish to'xtatildi.

        onClick(btnAll); // bu kod btnAll bosilmasa ham ,dastur ishga tushganda , barcha yozuvlarni ko'rsatish uchun

    }

    @SuppressLint("Range")
    @Override
    public void onClick(View v) {

        db = dbHelper.getWritableDatabase() ; // bazaga ulandik

        // edittext dagi matnlar o'zgaruchilarga o'zlashtirildi
        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        // query(so'rovlar) usulida qo'llaniladigan o'zgaruchilarni tavsifladik
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String distinct = null ;

        //cursor ni tavsifladik
        Cursor c = null ;

        switch (v.getId()){
            case R.id.btnAll:
                Log.d(LOG_TAG, "--- Barcha yozuvlar: ---");
                //
                c = db.query("mytable",null, null, null, null, null, null) ;
                break;
            case R.id.btnFunc:
                Log.d(LOG_TAG, "--- Funksiya " + sFunc + " ---");
                columns = new String[]{sFunc} ; // string tipli massiv, o'zgaruchisi - etFunc ga kelgan malumot
                //
                c = db.query("mytable",columns,null, null, null, null, null) ;
                break;
            case R.id.btnPeople: //aholisi ekranda kiritilgan raqamdan ko'p bo'lgan mamlakatlarni ko'rsatadi
                Log.d(LOG_TAG, "--- Alohi soni  " + sPeople + " dan ko'pi");
                selection  = "people > ?" ;
                selectionArgs = new String[]{sPeople} ;
                c = db.query("mytable",null,selection,selectionArgs,null,null,null) ;
                break;
            case  R.id.btnGroup: // mamlakatlarni mintaqalar bo'yicha guruhlash va umumiy aholini ko'rsatish
                Log.d(LOG_TAG,"Mintaqalar bo'yicha aholi soni") ;
                columns = new String[] {"region","sum(people) as people"} ;
                groupBy = "region" ; // guruhlash region bo'yicha bo'lishini anglatadi
                c = db.query("mytable",columns,null,null,groupBy,null,null) ;
                break;
            case R.id.btnHaving: // aholi soni ko'rsatilgan sondan ko'p bo'lgan hududlarni ko'rsatadi
                Log.d(LOG_TAG, "Aholi soni " + sRegionPeople+" dan ko'p bo'lgan hududlar");
                columns = new String[] {"region","sum(people) as people"} ;// shu "" ichindakila sql so'rovlari manimcha,shuni bir go'rishim garak
                groupBy = "region" ;
                having = "sum(people) >"+sRegionPeople ;
                c = db.query("mytable",columns,null,null,groupBy,having,null) ;
                break;
            case R.id.btnSort:
                switch (rgSort.getCheckedRadioButtonId()){// qaysi radio button bosilgani aniqlandi
                    case R.id.rName:
                        Log.d(LOG_TAG,"Nomi bo'yicha saralandi") ;
                        orderBy = "name" ; // saralash alifbo tartibida
                        break;
                    case R.id.rPeople:
                        Log.d(LOG_TAG,"Aholi bo'yicha saralandi") ;
                        orderBy = "people" ; // saralash o'sish tartibida
                        break;
                    case R.id.rRegion:
                        Log.d(LOG_TAG,"Region bo'yicha saralandi") ;
                        orderBy = "region" ; // saralash alifbo tartibida
                        break;
                }
                c = db.query("mytable",null, null, null, null, null, orderBy) ;
                break;
        }

        if (c!=null){// bazada yozuvlar borligi aniqlanyabti
            if (c.moveToFirst()){
                String str ;
                do {
                    str = "" ;
                    for (String cn:c.getColumnNames()){// ustun nomlari 'cn' o'zgaruvchisiga yuklatilyabti
                        // har bir maydon id si 'str' o'zgaruvchisiga olinyabti
                        str = str.concat(cn+" = "+c.getString(c.getColumnIndex(cn))+"; ") ;
                    }
                    Log.d(LOG_TAG,str) ;
                }while(c.moveToNext()) ;
            }
            c.close(); // so'rov yuborish to'xtatildi
        }else
            Log.d(LOG_TAG,"Cursor bo'sh") ;
        db.close(); // bazaga ulanish uzuldi
    }

    class DBHelper extends SQLiteOpenHelper{


        public DBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- Baza yaratildi! ---");
                db.execSQL("CREATE TABLE mytable (" +  "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "people INTEGER," +
                        "region TEXT" +  ") ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}