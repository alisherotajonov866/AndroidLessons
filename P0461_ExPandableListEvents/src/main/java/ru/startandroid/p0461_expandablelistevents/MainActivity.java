package ru.startandroid.p0461_expandablelistevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs" ;

    ExpandableListView elvMain; // listviewni topib olish uchun o'zgaruchi
    AdapterHelper ah; // AdapterHelper classimizdan obyekt yoki meros olish uchun shu class tipli o'zgaruchi
    SimpleExpandableListAdapter adapter; //
    TextView tvInfo ; // textView tipli o'zgaruchi,kerak bo'ladi bizga

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        ah = new AdapterHelper(this);// Adapter helper classimizdan obyekt olindi manimcha
        adapter = ah.getAdapter();// olingan obyektni getAdapter usulini chaqirib o'zlarimizni , shu classdagi adapterga o'rnatdik

        // listviewni topib oldik va unga adapterni o'rnatdik
        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        // elementni bosish
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(LOG_TAG,"onChild groupPosition = "+ groupPosition + " childPosition = " + childPosition + " id = " + id) ;

                // bosilgan element va uning guruhining matnini ko'rsatamiz
                tvInfo.setText(ah.getGroupChildText(groupPosition,childPosition));

                /*
                Usul mantiqiy qiymatni qaytarishi kerak . Agar biz true ga qaytsak, bu biz o'zimiz voqeani to'liq qayta ishlaganimiz va
                u boshqa ishlovchilarga (agar mavjud bo'lsa) o'tmasligi haqida xabar berayotganimizni anglatadi.
                Agar biz noto'g'ri qaytarsak, u holda voqeani yana davom ettirishga ruxsat beramiz .
                 */
                return false;
            }
        });

        // guruhni bosish
        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(LOG_TAG,"onGroupClick groupPosition = " + groupPosition + " id = " + id) ;

                /*
                Bu usul ham booleanni qaytarishi kerak . Guruh pozitsiyasi = 1 bo'lsa, biz rostni qaytaramiz,
                aks holda noto'g'ri. Bular. ushbu guruh uchun biz tadbirni keyingi qayta ishlashni bloklaymiz
                 */

                // katta guruhning ichidag turgan va pozitsiyasi 1 ga teng bo'lgan guruhni, ochilmaydigan qilib qo'ydik
                if (groupPosition == 1) return true;

                return false ;
            }
        });

        // guruhni yopilishi
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            // bu yerda groupPosition yopilgan guruhnig pozitsiyasi
            public void onGroupCollapse(int groupPosition) {
                Log.d(LOG_TAG,"onGroupCollapse groupPosition = " + groupPosition) ;
                tvInfo.setText("Yopilgan guruh: " + ah.getGroupText(groupPosition));
            }
        });

        // guruhning kengayishi
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            // bu yerda groupPosition kengaygan guruhning pozitsiyasi
            public void onGroupExpand(int groupPosition) {
                Log.d(LOG_TAG,"onGroupExpand groupPosition = " + groupPosition) ;
                tvInfo.setText(("Ochilgan guruh: "+ ah.getGroupText(groupPosition)));

            }
        });

        // 3-pozitsiyada kengaytirdik, ya'ni ilova ochilgani bilan 2-pozitsiyadagi guruh ochiladi avtomatik
        // indekslash 0 dan boshlangan, shuni esdan chiqarmasligim kerak!
        elvMain.expandGroup(2) ;
    }

    // 47 - dars uchun:
    // Adapter ma'lumotlar to'plami va ushbu ma'lumotlardan foydalanadigan ob'ekt o'rtasidagi ko'prikdir .
    //Shuningdek, adapter to'plamdagi har bir ma'lumot uchun View komponentini yaratish uchun javobgardir.
}