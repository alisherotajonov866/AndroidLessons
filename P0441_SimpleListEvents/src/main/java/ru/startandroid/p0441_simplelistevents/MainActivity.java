package ru.startandroid.p0441_simplelistevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs" ;

    ListView lvMain ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = findViewById(R.id.lvMain) ;

        // elementlar bor adi bizda , uni ekrana chiqarish garak, bunda bizga adapter yordam baradi
        // o'zgaruchilari so'ridi:
        // 1-o'zgaruvchi - this bo'ladi
        // 2-si - elementlarni qayerdan olishimiz
        //3-si - qayerda chiqarishimiz
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.names, android.R.layout.simple_list_item_1) ;

        // adapterni lvMain mizga ulimiz ish tamom shu bilan , elementlar ekranda chiqariladi
        lvMain.setAdapter(adapter); // shu yergacha oldinggi darsda o'rganganlarim

        //
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // methodning ayrim o'zgaruvchilari , o'zgartirildi
            // parent, position,id

            // Bu yerda parent - elementnig qayerda ko'rinish kerakligi,
            // biz parent dedik , yani bizning holatda parent -  ListView

            // view - yaxshi tushunmadim darsdagi gapa

            // position - ro'yxatdagi elementning seriya raqami,
            // POSITSIYA 1 dan emas 0 dan hisoblaydi, sanashni

            // id - element identifikatori
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ro'yxatdan bosilgan elementning pozitsiyasi va id si ekranga chiqarilyabti
                Log.d(LOG_TAG,"itemCLick: position = "+position + ", id = "+ id) ;
            }
        });

        //
        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // methodning ayrim o'zgaruvchilari , o'zgartirildi
            // parent, position,id
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // ro'yxatda tanlangan elementning pozitsiyasi bilan id si ekranga chiqarildi
                Log.d(LOG_TAG,"ItemSelect: position = " + position + ", id = "+ id) ;
            }

            // parentga o'zgartirildi
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG,"itemSelect: nothing") ;
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {

            // aylantirish holatlarini boshqarish uchun

            // method o'zgaruchilari o'zgartirildi
            // view - aylantirilgan elementlar yani avvalgi viewda ko'rsatilishini taminlaydi
            // ro'yxat holati, u 3 ta qiymatni qabul qilishi mumkun, ular haqida darsda batafsil aytilgan
            @Override
            public void onScrollStateChanged(AbsListView View, int scrollState) {
                Log.d(LOG_TAG,"ScrollState = " + scrollState) ;
            }

            //aylantirish bilan ishlash uchun

            // method o'zgaruvchilari o'zgartirildi

            // view - qaysi elementlar aylantirilishi, view deyilsa butun ekran nazarda tutiladi
            // firstVisibleItem - ekranda 1-ko'rinadigan ro'yxat elementi
            // visibleItemCount - ekranda nechta element ko'rinyapti
            //totalItemCount - ro'yxatda nechta element bor
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /*Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
                        + ", visibleItemCount = " + visibleItemCount
                        + ", totalItemCount = " + totalItemCount);*/
            }
        });

    }
}