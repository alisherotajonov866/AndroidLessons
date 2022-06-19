package ru.startandroid.p0131_menusimple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("menu1") ;
        menu.add("menu2") ;
        menu.add("menu3") ;
        menu.add("menu4") ;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this,item.getTitle()+" bosildi",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}