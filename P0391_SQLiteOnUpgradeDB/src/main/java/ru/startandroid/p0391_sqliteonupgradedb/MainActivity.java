package ru.startandroid.p0391_sqliteonupgradedb;

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

    final String DB_NAME = "staff" ;
    final int DB_VERSION = 2 ; // oldin 1 edi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbh = new DBHelper(this) ;// bazani boshqarish uchun DBOpenHelperdan meros olgan classdan obyket oldik
        SQLiteDatabase db = dbh.getWritableDatabase() ;
        Log.d(LOG_TAG,"Staff db ning versiyasi - "+db.getVersion()) ;// staff nomli bazamizning dastalbki versiyasini ko'rsatib o'tdik
        writeStaff(db) ; // db obyektini writeStaff methodiga yubordik
        dbh.close();
    }

    void writeStaff(SQLiteDatabase db) {// Cursor orqali people jadvalidagi malumotlarni olib beradigan method
        // rawQuery - bu sqlni tushunadigan odamlar uchun so'rov turi
        // Query esa - sqlni tushunmaydigan odamlar uchun
        Cursor c = db.rawQuery("SELECT * FROM people",null) ;// cursor obyekti people jadvalidan barcha malumotlarni o'qib keldi
        logCursor(c,"Table people") ;// o'qilgan malumotlarni logCursor jadvaliga yubordik,yangi Table people jadval nomi bilan
        c.close();// jadvaldan malumot o'qish to'xtatildi

        c = db.rawQuery("SELECT * FROM position",null) ;
        logCursor(c,"Table position");
        c.close();

        // people jadvali bilan position jadvali inner join orqali birlashtirildi
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id ";
        c = db.rawQuery(sqlQuery,null) ;// Cursor orqali bazaga hosil bo'lga yangi jadvalni olish uchun so'rov yuborildi
        logCursor(c,"inner join");// hosil bo'lgan jadval ko'rish uchun logCursorga yuborildi
        c.close();
    }

    @SuppressLint("Range")
        // bu method bizga so'rov orqali Cursorda turgan jadvaldagi malumotlarni olishga yordam beradi
    void logCursor(Cursor c, String title) {
        if (c!=null){
            if(c.moveToFirst()){
                Log.d(LOG_TAG,title + " . " + c.getCount()+" qatorga ega") ; // c.getCount bizga jadvaldagi qatorlar sonini topib beradi
                StringBuilder sb = new StringBuilder() ;// Cursor dagi malumotlarni yuklash va shu orqali chiqarish uchun foydalaniladi
                do {
                    sb.setLength(0) ;
                    for (String cn: c.getColumnNames()){// biror cn(Column Names) String o'zgaruchisiga 'c' obyketidan jadval nomlari o'zlashtirildi
                        sb.append(cn + " = " + c.getString(c.getColumnIndex(cn))+";") ;// jadval malumtlari birin-ketin StringBuilder obyketiga yuklatilyabti
                    }
                    Log.d(LOG_TAG,sb.toString()) ;// Log_tag orqali String Builder obyekti(sb) dagi malumotlar chiqarilyabti
                }while (c.moveToNext()) ;
            }
            else
                Log.d(LOG_TAG,title + ". Cursor bo'sh") ;
        }
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, " --- onCreate database --- ");

            // kod o'zgartirildi, yangi versiyadan birdaniga foyalanadiganlar uchun

            // people jadvali uchun malumollar
            String people_name [] = {"Alisher","Elyor","Javohir","Zafar","Sardor","Jasur","Fayzulla","Diyor"} ;
            int people_posid[] = {2, 3, 2, 2, 3, 1, 2, 4 } ;

            // position jadvali uchun malumotlar
            int[] position_id = { 1, 2, 3, 4 };
            String[] position_name = { "Директор", "Программер", "Бухгалтер",
                    "Охранник" };
            int[] position_salary = { 15000, 13000, 10000, 8000 };

            ContentValues cv = new ContentValues() ;

            db.execSQL("create table position (" + "id integer primary key,"
                    + "name text, salary integer" + ");");

            for (int i = 0; i < position_id.length; i++) {
                cv.clear();
                cv.put("id", position_id[i]);
                cv.put("name", position_name[i]);
                cv.put("salary", position_salary[i]);
                db.insert("position", null, cv);
            }

            db.execSQL("CREATE TABLE people (" + "id integer primary key autoincrement," + "name text," + "posid integer" + ") ; ");

            for (int i=0;i<people_name.length;i++){
                cv.clear();// qutini dastlab tozalab olish kerak
                cv.put("name",people_name[i]);
                cv.put("posid",people_posid[i]);
                db.insert("people",null,cv) ;//qutidagi malumotlar bazaga qo'shildi
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(LOG_TAG,"Eski versiya - " + oldVersion + "  Yangisiga o'zgartirildi - "+ newVersion) ;

            if (oldVersion == 1 && newVersion == 2){
                // agar rostdan ham versiya o'zgartirilgan bo'lsa, bazaga kod yuborish uchun 'quti' tayyorlanyabti
                ContentValues cv = new ContentValues() ;
                int [] position_id = {1,2,3,4} ;
                String [] position_name = {"Direktor","Dasturchi","Bug'galter","Oxran"} ;
                int [] position_salary = {15000,13000,10000,8000} ;

                db.beginTransaction();// tranzaksiya boshlandi
                /*
                tranzaksiyadan foydalanishdan maqsad - xatolik yuz bergan taqdirda, barcha o'zgarishlar bekor qilinishi
                va ma'lumotlar bazasi avvalgidek bo'lib qolishi uchun
                 */
                try {
                    db.execSQL("CREATE TABLE position ("+ "id integer primary key," + "name text," + "salary integer" +")");

                    for (int i=0;i<position_id.length;i++){
                        cv.clear() ;
                        cv.put("id",position_id[i]);
                        cv.put("name",position_name[i]);
                        cv.put("salary",position_salary[i]);
                        db.insert("position",null,cv) ;// qutida hosil bo'lga malumotlarni bazaga qo'shdik
                    }
                    // drop table - jadvalni butunlay o'chiradi
                    // alter table - jadvalni o'zgartirish buyruqi
                    db.execSQL("ALTER TABLE people ADD COLUMN posid INTEGER;");//people jadvaliga ustun qo'shildi, bu ustunning nomi
                    // 'posid' bo'ladi, ! INTEGER tipli, bu ustunga position jadvalining 'id' keladi

                    for (int i=0;i<position_id.length;i++){
                        cv.clear();
                        cv.put("posid",position_id[i]);

                        // position = ? va undan keyingi kodga yaxshi tushunmadim
                        db.update("people",cv,"position = ?", new String[] { position_name[i] }) ;

                        /*
                         TEPADIGI koddan keyin people jadvalida position ustuni o'chib ketmadi, uning yoniga posid ustuni borib qo'shildi
                        endi biz position ustunini o'chirib tashlashimiz zarur, lekin buni oddiy qilib amalga oshira olmaymiz
                         bizga vaqtinchalik jadval kerak bo'ladi

                        */

                        /*
                        siz vaqtinchalik jadval yaratishingiz, u erda ma'lumotlarni uzatishingiz, asl nusxani o'chirishingiz, uni kerakli tuzilma bilan qayta yaratishingiz,
                         vaqtinchalik jadvaldan ma'lumotlarni unga tushirishingiz va vaqtinchalik jadvalni o'chirishingiz kerak.
                         */
                        // vaqtinchalik jadval yaratib olindi, bir nechta ustunlar bilan birga, huddi people jadvaliga o'hshagan
                        db.execSQL("CREATE TEMPORARY TABLE people_tmp (" + "id integer," + "name text," + "position text," + "posid integer" + ");");

                        // INSTER INTO (ichiga qo'sh) buyruqi bilan people jadvalidan ustunlar belgilanib(SELECT orqali) ustunlardagi malumotlar people_tmp jadvaliga yuborilyabti
                        db.execSQL("INSERT INTO people_tmp SELECT id,name,posid FROM people;");
                        db.execSQL("DROP TABLE people;"); // people jadvali o'chirildi

                        // people jadvali yaratildi
                        db.execSQL("create table people ("
                                + "id integer primary key autoincrement,"
                                + "name text, posid integer);");

                        // INSTER INTO (ichiga qo'sh) buyruqi bilan people_tmp jadvalidan ustunlar belgilanib(SELECT orqali) ustunlardagi malumotlar people jadvaliga yuborilyabti
                        db.execSQL("insert into people select id, name, posid from people_tmp;");
                        db.execSQL("drop table people_tmp;"); // people_tmp jadvali o'chirildi

                        db.setTransactionSuccessful();// agar trankzaksiya begin dan boshlanib to shu joygacha yetib kelsa
                        // tranzaksiya omadli yakunlangan bo'ladi va begin va successful lar orasidagi kod bajariladi aks holda
                        // bajarilmaydi.
                    }
                }finally {
                    db.endTransaction();// tranzaksiya yakunlandi
                }

                // MUHIM !
                // Ilovamiz yangilandi . Va endi, u ishga tushirilganda, u 2 -versiya ma'lumotlar bazasiga ulanishga harakat qiladi ,
                // lekin u mavjud versiya = 1 ekanligini ko'radi va onUpgrade usulini chaqiradi ,
                // bu bizga ma'lumotlar bazasi tuzilishiga kerakli o'zgartirishlar kiritish imkoniyatini beradi.
                // Ammo bu ilova yangilangan taqdirda sodir bo'ladi.
                // Agar foydalanuvchi bizning yangi ilovamizni birinchi marta yangi smartfonga qo'ysa nima bo'ladi?

                /*
                Bunday holda, dastur 2 -versiya ma'lumotlar bazasiga ulanishga ham harakat qiladi . Ammo beri ilova endigina o'rnatildi,
                 ma'lumotlar bazasi hali mavjud emas. Ilova ma'lumotlar bazasini yaratadi va unga 2 -versiyani beradi .
                 bu versiya bilan ishlashi mumkin. Yaratilganda DBHelper - dagi onCreate usuli chaqiriladi . Bu shuni anglatadiki,
                 unda biz uchun 2 -versiya ma'lumotlar bazasini yaratadigan kodni yozishimiz kerak - ya'ni . yangilangan odamlar jadvali va yangi lavozim jadvali .
                 */

            }

        }
    }
}