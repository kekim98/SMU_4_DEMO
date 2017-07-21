package example.com.smu_4_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView nameList = (ListView) findViewById(R.id.nameList);
        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        nameList.setAdapter(mListAdapter);
        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long I) {
                String name = (String) adapterView.getAdapter().getItem(i);
                SharedPreferences pref = AddViewActivity.getPref(MainActivity.this);
                String code = pref.getString(name, "UNKNOWN");

                Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("code", code);
                startActivity(intent);

                /*Button exitbutton = (Button) findViewById(R.id.exitbutton);
                exitbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("정말로 종료하시겠습니까?");
                        builder.setTitle("종료 알림창")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(MainActivity.this, AddViewActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(MainActivity.this, AddViewActivity.class);
                                        startActivity(intent);

                                    }
                                });
                        builder.show();
                    }
                });*/
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity", "onResumeeeeeeeeeee");
        refresh();
    }

    private void refresh() {
        Log.d("MainActivity", "refreshhhhhhhhh");
        SharedPreferences pref = AddViewActivity.getPref(this);

        mListAdapter.clear();
        Map<String, ?>values = pref.getAll();
        for (String key:values.keySet()){
            Log.d("aaa", "" + key);
            mListAdapter.add(key);
        }
        mListAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.code_name, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu:
                intent = new Intent(this, AddViewActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
