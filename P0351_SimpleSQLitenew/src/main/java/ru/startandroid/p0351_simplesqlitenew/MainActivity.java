package ru.startandroid.p0351_simplesqlitenew;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final  String LOG_TAG = "myLogs" ;

    Button btnAdd,btnRead,btnClear,btnUpd,btnDel ;
    EditText etName,etEmail,etID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class DPHelper extends SQLiteOpenHelper {
        public DPHelper(@Nullable Context context) {
            super(context, "MyDB", null, 1);
        }
    }
}