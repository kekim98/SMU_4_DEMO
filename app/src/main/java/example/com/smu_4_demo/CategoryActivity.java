package example.com.smu_4_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    ListView categoryview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryview = (ListView) findViewById(R.id.categoryview);

        String myStringArray[] = {"1.학번과 이름 저장", "2.학번과 이름 저장", "3.학번과 이름 저장", "4.학번과 이름 저장", "5.학번과 이름 저장"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);
        categoryview.setAdapter(adapter);

        categoryview.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(CategoryActivity.this, item, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CategoryActivity.this, AddViewActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
