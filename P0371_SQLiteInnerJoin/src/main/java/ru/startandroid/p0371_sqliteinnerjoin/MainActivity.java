package ru.startandroid.p0371_sqliteinnerjoin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs" ;

    // bazadagi malumotlar bilan ishlashimiz uchun, shu massivdagi elementlarni bazaga o'qib olib ulardan foydalanib turamiz.
    // bular 1-jadval uchun
    int position_id [] = {1,2,3,4} ;
    String position_name [] = {"Direktor","Dasturchi","Buggalter","Ohran"} ;
    int position_salary [] = {15000, 13000, 10000, 8000} ;

    //2-jadval uchun
    String people_name[] = {"Alisher","Diyor","Fayzulla","Jasur","Baxromjon","Sardor","Ibrohim","Zafar"} ;
    int people_posid[] = {2, 3, 2, 2, 3, 1, 2, 4 } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbh = new DBHelper(this) ;// Malumotlar bazasini boshqarish uchun DBHelper classidan obyekt oldik
        // Bazada biror narsa yaratmoqchi bo'lsak onCreate usulining 'db' o'zgaruchisiga qiymat yuklatishimiz zarur
        SQLiteDatabase db = dbh.getWritableDatabase() ; // dbh obyekti orqali bazaga ulanildi

        Cursor c ;

        Log.d(LOG_TAG, "--- Table position ---");
        // position jadvalini ko'rsatish uchun so'rov yuborildi
        c = db.query("position",null, null, null, null, null, null) ;
        logCursor(c) ;
        c.close();// so'rov to'xtatildi
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "--- Table people ---");
        c = db.query("people",null, null, null, null, null, null) ;
        logCursor(c) ;
        c.close();
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "--- INNER JOIN with rawQuery---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";
        c = db.rawQuery(sqlQuery,new String[]{"12000"}) ;
        logCursor(c) ;
        c.close();
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "--- INNER JOIN with query---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id" ;
        String columns[] = {"Pl.name as Name", "PS.name as Position", "salary as Salary"} ;
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        c = db.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");

        db.close();
    }

    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                } while (c.moveToNext());
            }
        } else
            Log.d(LOG_TAG, "Cursor is null");
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(@Nullable Context context) {
            super(context, "mytable", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG,"Baza yaratildi!");

            ContentValues cv = new ContentValues() ; // bazaga malumot olib boradigan "quti" dan obyekt olindi

            // 1-baza yaratildi, lekin u hali bo'sh
            db.execSQL("CREATE TABLE position (" + "id integer primary key autoincrement," + "name text," + "salary integer" + ") ; ");

            // 1-bazaga massivlardan malumot qo'shildi
            for (int i=0;i<position_id.length;i++){
                cv.clear(); // quti bo'shatildi
                cv.put("id",position_id[i]);
                cv.put("name",position_name[i]);
                cv.put("id",position_salary[i]);
                // qutidagi malumotlarni 'position' jadvaliga yuborildi
                db.insert("position",null,cv) ;
            }

            // 2-baza yaratildi, lekin u hali bo'sh
            db.execSQL("CREATE TABLE people (" + "id integer primary key autoincrement," + "name text," + "posid integer" + ") ; ");

            // 2-bazaga massivlardan malumot qo'shildi
            for (int i=0;i<people_name.length;i++){
                cv.clear(); // quti bo'shatildi
                cv.put("name",people_name[i]);
                cv.put("posid",people_posid[i]);
                // qutidagi malumotlarni 'position' jadvaliga yuborildi
                db.insert("people",null,cv) ;
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}