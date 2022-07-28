package ru.startandroid.p0451_expandablelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // ichida elementlari bo'lgan massivlarni , o'z ichiga oladigan ota massivi
    String [] groups = {"HTC", "Samsung", "LG"} ;

    // ota massivning bolalari
    String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    //guruhlar uchun to'plam tavsiflandi
    ArrayList<Map<String, String>> groupData ;

    // elementlar uchun to'plam tavsiflandi
    ArrayList<Map<String,String>> childDataItem ;

    // elementlar uchun to'plam tavsiflandi
    ArrayList<ArrayList<Map<String, String>>> childData ;

    // atributlar uchun Map tavsiflandi
    Map<String, String> m ;

    ExpandableListView elvMain ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ArrayList dan groupData nomli obyket olindi
        groupData = new ArrayList<Map<String, String>>() ;

        for (String group : groups){// groups massividagi elementlar biror group nomli String tipli o'zgaruchiga kelib tushyabti

            // HashMap dan 'm' nomli obyekt olindi
            m = new HashMap<String, String>() ;

            // 'm' nomli obyektga element qo'shilyabti
            m.put("groupName",group) ;

            // groupData to'plamiga 'm' obyektiga kelib tushgan malumotlar qo'shildi
            groupData.add(m) ;
        }

        // String tipli massiv
        // o'qilishi kerak bo'lgan atribut nomlari ro'yxati
        String groupFrom[] = new String[] {"groupName"} ;

        // int tipli massiv
        // o'qilgan atribut qiymatlari joylashtiriladigan ko'rish elementi identifikatorlari ro'yxati
        int groupTo[] = new int[] {android.R.id.text1} ;


        // obyekt olindi
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // obyekt olindi
        childDataItem = new ArrayList<Map<String, String>>() ;

        for (String phone : phonesHTC){
            m = new HashMap<String, String>() ;
            m.put("phoneName",phone) ;
            childDataItem.add(m) ;
        }

        childData.add(childDataItem) ;

        // bir xil kod takrorlandi, to'plam ichida turgan , to'plamning elementlari joylanyabti
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // bir xil kod takrorlandi, to'plam ichida turgan , to'plamning elementlari joylanyabti
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String childFrom [] = new String[] {"phoneName"} ;

        int childTo[] = new int[] {android.R.id.text1} ;

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
           this, // kontekst
           groupData, // to'plamlarni o'z ichiga oluvchi , to'plam ko'rsatilishi kerak , bu bizda groupData
           android.R.layout.simple_expandable_list_item_1,//ro'yxatdagi guruhni ko'rsatish uchun foydalaniladigan tartib resursi
           groupFrom, //
           groupTo, //
           childData,// katta to'plamdan keyin turuvchi to'plam
           android.R.layout.simple_list_item_1, //kichkina to'plam elementlari qanday ko'rsatilishi
           childFrom, //
           childTo //
        ) ;
        elvMain = findViewById(R.id.elvMain) ;
        elvMain.setAdapter(adapter);
    }
}