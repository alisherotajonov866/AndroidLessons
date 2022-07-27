package ru.startandroid.p0411_layoutinflaterlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    String[] position = { "Программер", "Бухгалтер", "Программер", "Программер", "Бухгалтер", "Директор", "Программер", "Охранник" };
    int salary[] = { 13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000 };

    // bizga malumotlar zebra bo'lib ko'rinishi uchun foydalanildi
    int [] colors = new int[2] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");

        LinearLayout linLayout = findViewById(R.id.linLayout) ;

        LayoutInflater ltInflater = getLayoutInflater() ;

        for (int i=0;i<name.length;i++){
            Log.d("myLogs","i = " + i) ;
            // inflate methodining ichida birinchi o'zgaruchini berishda yani elementni qaysi
            // xml dan olishimizni ko'rsatmoqchi bo'lganimizda R.layout. deb qidiriladi. (R.id.) deb emas.
            View item = ltInflater.inflate(R.layout.item,linLayout,false) ;

            TextView tvName = item.findViewById(R.id.tvName) ;// item.xml dagi tvName ni topib oldik
            tvName.setText(name[i]);// name massivini 1-elementini tvName yubordik

            TextView tvPosition = item.findViewById(R.id.tvPosition) ;
            tvPosition.setText("Lavozim: "+position[i]);

            TextView tvSalary = item.findViewById(R.id.tvSalary) ;
            // String.valueOf - barcha tiplarni string qiymatga aylantirib beruvchi method.
            tvSalary.setText("Maosh: "+String.valueOf(salary[i]));

            // item ga item.xml dan kelgan bir butun elementni uzunligini match_parentga tengladik
            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT ;

            // Bu holatda bir safar colors massivining bir elementiga, bir safar
            // boshqa rangdagi elementiga murojat bo'ladi, chunki 2 ga bo'lib qoldiq olinsa,
            // yo 0 yo 1 bo'ladi, shu bulan jadval zebraga o'hshash bo'ladi
            item.setBackgroundColor(colors[i % 2]);

            // inflate methodini false qilganimizni sababi item.xml dan malumot olib uni darhol chiqarmasligimiz,
            // biz hozir uni olib bo'lganimizdan keyin ,uning ustuda bir qancha ishlar bajardik
            linLayout.addView(item);
        }
    }
}