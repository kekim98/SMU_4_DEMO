package example.com.smu_4_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Map;

public class List_ViewActivity extends AppCompatActivity {

    private ArrayAdapter<String> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__view);
        ListView nameList = (ListView) findViewById(R.id.nameList);
        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        nameList.setAdapter(mListAdapter);
        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long I) {
                String name = (String) adapterView.getAdapter().getItem(i);
                SharedPreferences pref = AddViewActivity.getPref(List_ViewActivity.this);
                String code = pref.getString(name, "UNKNOWN");

                Intent intent = new Intent(List_ViewActivity.this, DetailViewActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("code", code);
                startActivity(intent);
            }
        });

        Button backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_ViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity", "onResume");
        refresh();
    }

    private void refresh() {
        Log.d("MainActivity", "refresh");
        SharedPreferences pref = AddViewActivity.getPref(this);

        mListAdapter.clear();
        Map<String, ?> values = pref.getAll();
        for (String key:values.keySet()){
            Log.d("aaa", "" + key);
            mListAdapter.add(key);
        }
        mListAdapter.notifyDataSetChanged();
        }
    }
