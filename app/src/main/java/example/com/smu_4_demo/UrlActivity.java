package example.com.smu_4_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class UrlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        Button backbt = (Button) findViewById(R.id.backbt);
        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UrlActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        WebView url = (WebView) findViewById(R.id.url);
        url.loadUrl("https://www.smu.ac.kr/mbs/smu/intro.jsp");
    }
}
