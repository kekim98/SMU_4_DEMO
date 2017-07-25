package example.com.smu_4_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import static example.com.smu_4_demo.R.id.name;
import static example.com.smu_4_demo.R.id.nameList;

public class List_ViewActivity extends AppCompatActivity {

    private ArrayAdapter<String> mListAdapter;
    private ArrayList<String> items = null;
    private static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__view);

        final ListView nameList = (ListView) findViewById(R.id.nameList);
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

        nameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final String name = (String) adapterView.getAdapter().getItem(i);
                    AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
                    alertDlg.setTitle("삭제 알림창");
                    alertDlg.setMessage("정말로 삭제하시겠습니까?");

                    alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences pref = getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();

                            editor.remove(name);
                            editor.commit();
                            mListAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });

                    alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDlg.show();
                    return false;
                }
            });
        }





        @Override
        protected void onResume() {
            super.onResume();
            Log.d("MainActivity", "onResume");
            refresh();
        }

        private void refresh() {
            Log.d("MainActivity", "refresh");
            SharedPreferences pref = AddViewActivity.getPref(this);

            mListAdapter.clear();
            Map<String, ?> values = pref.getAll();
            for (String key : values.keySet()) {
                Log.d("aaa", "" + key);
                mListAdapter.add(key);
            }
            mListAdapter.notifyDataSetChanged();
        }
        }


