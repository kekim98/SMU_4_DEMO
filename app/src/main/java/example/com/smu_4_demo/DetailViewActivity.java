package example.com.smu_4_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        TextView nameText = (TextView) findViewById(R.id.name);
        TextView codeText = (TextView) findViewById(R.id.code);

        Intent recvIntent = getIntent();
        Bundle extras = recvIntent.getExtras();
        String name = extras.getString("name", "UNKNOWN");
        String code = extras.getString("code", "UNKNOWN");

        nameText.setText(name);
        codeText.setText(code);

        Button button = (Button) findViewById(R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailViewActivity.this, List_ViewActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
