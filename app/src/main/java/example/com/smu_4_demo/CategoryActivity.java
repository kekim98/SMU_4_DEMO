package example.com.smu_4_demo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    ListView categoryview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryview = (ListView) findViewById(R.id.categoryview);

        String myStringArray[] = {"1. 회원정보 저장", "2. 공지사항", "3. 웨이트장 사용시 유의사항", "4. 이용안내", "5. 위치안내", "6. 학교 홈페이지"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myStringArray);
        categoryview.setAdapter(adapter);

        categoryview.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if(position == 0){
                            Intent intent = new Intent(CategoryActivity.this, AddViewActivity.class);
                            startActivity(intent);
                        }

                        if(position == 1){
                            Intent intent = new Intent(CategoryActivity.this, NoticeActivity.class);
                            startActivity(intent);
                        }

                        if(position == 2){
                            Intent intent = new Intent(CategoryActivity.this, NoteActivity.class);
                            startActivity(intent);
                        }

                        if(position == 3){
                            Intent intent = new Intent(CategoryActivity.this, UseActivity.class);
                            startActivity(intent);
                        }

                        if(position == 4){
                            Intent intent = new Intent(CategoryActivity.this, MapActivity.class);
                            startActivity(intent);
                        }

                        if(position == 5){
                            Intent intent = new Intent(CategoryActivity.this, UrlActivity.class);
                            startActivity(intent);
                        }
                    }
                }
        );

        Button btback = (Button) findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
