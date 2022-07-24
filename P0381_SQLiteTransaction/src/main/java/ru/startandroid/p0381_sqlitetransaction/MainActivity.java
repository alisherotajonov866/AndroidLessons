package ru.startandroid.p0381_sqlitetransaction;

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

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs" ;

    DBHelper dbh ;
    SQLiteDatabase db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "--- onCreate Activity ---");
        dbh = new DBHelper(this) ; // Bazani boshqarish uchun DBHelper classidan obyekt olindi
        myActions() ;// myActions usulini avtomatik ishga tushirish uchun
    }

     void myActions() {
        /*

        db = dbh.getWritableDatabase() ; //bazaga ulanildi, ya'ni 'dbh' obyekti yozish usulini chaqirdi va uni yaratishda
        // bizga yordam beradigan onCreate usulining 'db' nomli o'zgaruvchisiga malumot yuklashni boshlayabti.
         delete(db,"mytable") ; // jadvalni tozalaymiz
         db.beginTransaction();
         insert(db,"mytable","val1") ; // val1 qiymatga ega qatorni kiritamiz
         db.setTransactionSuccessful();
         insert(db,"mytable","val2");
         db.endTransaction();
         // methodni qavslarini ochib bo'lgandan keyin , dastur qursor yordamida biza qavs ichina novvi yozishimiz garak akanlikini aytib
         //boradi akan o'zi, qoyil !
         insert(db,"mytable","val3");
         read(db,"mytable") ; // jadvaldagi barcha yozuvlarni chop qilamiz
         dbh.close();// db o'zgaruchisiga malumot yozish to'xtatildi

         */
         try {
             db = dbh.getWritableDatabase();
             delete(db,"mytable");

             db.beginTransaction();
             insert(db,"mytable","val1");

             Log.d(LOG_TAG, "create DBHelper");
             DBHelper dbh2 = new DBHelper(this) ; // ma'lumotlar bazasiga yangi ulanishni yaratamiz
             Log.d(LOG_TAG, "get db");
             SQLiteDatabase db2 = dbh2.getWritableDatabase() ;
             read(db2,"mytable");
             dbh2.close();

             db.setTransactionSuccessful();
             db.endTransaction();

             read(db,"mytable");
             dbh.close();
         }catch (Exception ex){
             Log.d(LOG_TAG,ex.getClass() + "Error:" + ex.getMessage()) ;
         }
    }


    void insert(SQLiteDatabase db, String table, String value) {
        Log.d(LOG_TAG, "Insert in table " + table + " value = " + value);
        ContentValues cv = new ContentValues() ;// bazaga malumot olib boradigan quti
        cv.put("val",value);
        db.insert("mytable",null,cv) ;

    }

    @SuppressLint("Range")
    void read(SQLiteDatabase db, String table) {
        Log.d(LOG_TAG, "Read table " + table);
        // null dan aldin ahamiyat barib qarasam , hamma null larni oldinda bir xil narsa yozilmagan akan.
        Cursor c = db.query(table, null, null, null, null, null, null);
        if (c!=null){
            Log.d(LOG_TAG, "Records count = " + c.getCount());
            if (c.moveToFirst()){
                do {
                    Log.d(LOG_TAG, c.getString(c.getColumnIndex("val")));// val ustunidagi malumotlar chiqarilyapti
                }while (c.moveToNext()) ;
            }
            c.close();
        }
    }

    void delete(SQLiteDatabase db, String table) {
        Log.d(LOG_TAG, "Delete all from table " + table);
        db.delete("mytable",null,null) ;
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
            // bu yerdagi myDB - bu baza
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG,"Baza yaratildi") ;

            db.execSQL("CREATE TABLE mytable (" + "id integer primary key autoincrement," + "val text" + "); ");
            // bu yerdagi mytable - bu myDB dagi biror bir jadval, u yerda (myDB) undan tashqari jadvallar ham bo'lishi mumkun.

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}