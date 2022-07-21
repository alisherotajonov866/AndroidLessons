package ru.startandroid.p0341_simplesqlite;

import static android.os.Build.ID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs" ;

    Button btnAdd,btnRead,btnClear,btnUpd,btnDel ;
    EditText etName,etEmail,etID ;

    DBHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd) ;
        btnAdd.setOnClickListener(this);

        btnRead = findViewById(R.id.btnRead) ;
        btnRead.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear) ;
        btnClear.setOnClickListener(this);

        btnUpd = findViewById(R.id.btnUpd) ;
        btnUpd.setOnClickListener(this);

        btnDel = findViewById(R.id.btnDel) ;
        btnDel.setOnClickListener(this);

        etName = findViewById(R.id.etName) ;
        etEmail = findViewById(R.id.etEmail) ;
        etID = findViewById(R.id.etID) ;

        dbHelper = new DBHelper(this) ;
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues() ;

        String name = etName.getText().toString() ;
        String email = etEmail.getText().toString() ;
        String id = etID.getText().toString() ;

        SQLiteDatabase db = dbHelper.getWritableDatabase() ;

        switch (v.getId()){
            case R.id.btnAdd:
                Log.d(LOG_TAG, "Ma'lumot qo'shildi.");

                cv.put("name",name);
                cv.put("email",email);

                long rowID = db.insert("mytable",null, cv) ;

                Log.d(LOG_TAG, "Qo'shilgan malumot ID si = " + rowID);
                break;

            case R.id.btnRead:
                Cursor c = db.query("mytable",null,null,null,null,null,null) ;

                if(c.moveToFirst()){

                    Log.d(LOG_TAG, "Topilgan malumotlar:");

                    int idColIndex = c.getColumnIndex("id") ;
                    int nameColIndex = c.getColumnIndex("name") ;
                    int emailColIndex = c.getColumnIndex("email") ;

                    do {
                        Log.d(LOG_TAG,"ID = " + c.getInt(idColIndex) + ", name = " + c.getString(nameColIndex) + ", email = " + c.getString(emailColIndex));
                    } while (c.moveToNext());

                }
                else
                    Log.d(LOG_TAG, "Malumot topilmadi!");
                c.close();
                break;

            case R.id.btnClear:
                Log.d(LOG_TAG,"Ma'lumotlar bazasi tozalandi") ;
                int clearCount = db.delete("mytable",null,null) ;
                Log.d(LOG_TAG, "O'chirilgan malumotlar soni = " + clearCount);
                break;

            case R.id.btnUpd:
                if (id.equalsIgnoreCase("")){
                    break;
                }
                Log.d(LOG_TAG,"Baza yangilandi!") ;

                cv.put("name",name);
                cv.put("email",email);

                int updCount = db.update("mytable", cv ,"id = ?", new String[] { id } ) ;
                Log.d(LOG_TAG,"Yangilangan malumotlar soni = " + updCount) ;
                break;

            case R.id.btnDel:
                if (id.equalsIgnoreCase("")){
                    break;
                }
                Log.d(LOG_TAG,"Bazadan malumot o'chirildi") ;

                int delCount = db.delete("mytable","id = "+ id,null) ;
                Log.d(LOG_TAG,"O'chirilgan malumotlar soni: " + delCount) ;
                break;
        }

        dbHelper.close();
    }
// SQLiteOpenHelper classidan meros oldik.
    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "Baza yaratildi!");
            db.execSQL("CREATE TABLE mytable (" + " id INTEGER PRIMARY KEY AUTOINCREMENT," + " name text," + " email text " + " );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}