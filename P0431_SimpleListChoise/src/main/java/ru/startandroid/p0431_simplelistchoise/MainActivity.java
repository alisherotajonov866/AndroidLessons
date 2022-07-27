package ru.startandroid.p0431_simplelistchoise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs" ;

    ListView lvMain ;
    String [] names ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = findViewById(R.id.lvMain) ;

        // Bu kod bizga ro'yxat elementlarini belgilashimiz va undan keyin foydalanishimizga yordam beradi
        //lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE); oldingi kod, bu faqat ro'yxatdan bitta elementni tanlashga yordam berar edi

        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);// yangi kod,bu bir nechtasini tanlashga yordam beradi

        // ro'yxatni olish uchun adapter konsturktori
        // 1- va 2- o'zgaruvchilar tushunarli
        // 3- o'zgaruvchi bunday jarayonlardan shundan foydalanish maqsadga muvoffiq akan ))
        // createFromResource - bunday jarayonlardan shundan foydalanish maqsadga muvoffiq akan ))
        // oldin 3-o'zgaruchi ,simple_list_item_single_choice edi, bunda faqat 1 te element tanlash mumkun edi, endi bir nechta
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.names, android.R.layout.simple_list_item_multiple_choice) ;

        lvMain.setAdapter(adapter);

        Button btnChecked = findViewById(R.id.btnChecked) ;
        btnChecked.setOnClickListener(this);

        // xml faylda turgan massiv elementlari bizning names ga tushdi
        names = getResources().getStringArray(R.array.names) ;
    }

    @Override
    public void onClick(View v) {

        // oldingi kod
        // names [] massivi shunday elementlarni oladi keyin , ular lvMain da belgilangan pozitsiyadagilari bo'ladi
        //Log.d(LOG_TAG,"checked: " + names[lvMain.getCheckedItemPosition()]) ;

        Log.d(LOG_TAG, "checked: ");

        // Bu Map ro'yxatdagi elementlar tanlanganmi yoki yo'q, shuni o'zida saqlaydi, int va boolen tipli
        // int da kalitlar turadi,boolean da qiymat
        SparseBooleanArray sbArray = lvMain.getCheckedItemPositions() ;
        for (int i=0;i<sbArray.size();i++){//length amas , siz akan bindo
            int key = sbArray.keyAt(i) ;// MAP kalitini iteratsiyaga mos ravishda biror int tipli key o'zgaruvchisiga o'zlashtiryapti
            if (sbArray.get(key))// shu kalitga mos ro'yxat elementi belgilanganmi, shuni teshkiryapti
                Log.d(LOG_TAG,names[key]) ;

        }
    }
}