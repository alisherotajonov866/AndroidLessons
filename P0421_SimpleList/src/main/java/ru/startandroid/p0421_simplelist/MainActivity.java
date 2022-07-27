package ru.startandroid.p0421_simplelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // massiv ochib oldik
    String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml fayldagi listview ni topib oldik
        ListView lvMain = findViewById(R.id.lvMain) ;

       //1-o'zgaruvchi - this bo'ladi (yodlab qo'yish kerak (-:
        // 2-o'zgaruvchi - no'chin vi narsala yozilganini darsda tushuntirib qo'yibdi lekin man yaxshi tushunmadim
        // 3-o'zgaruvchi - malumotlar olinadigan joy, bizda hozir names nomli string massiv

        // Adapter lar bizga biz inlate usuli bilan qilgan ishimizni o'zi bajarib ishimizni osonlashtirib beradi
        // lekin ishlashi huddi inflate dagidek,yani biz ko'rsatgan xml  faylda textview bo'ladi
        //unga massivdagi elementlarni joylab , uni asosiy xml faylimizga yuboradi, meni tushunishim bo'yicha

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list_item,names) ;

        // listmain mizga adapter nomli obyketni uladik
        lvMain.setAdapter(adapter);
    }
}