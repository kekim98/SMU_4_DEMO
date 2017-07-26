package example.com.smu_4_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    double a, b, c = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calc);

        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        final TextView result = (TextView) findViewById(R.id.result);

        a = Double.parseDouble(num1.getText().toString());
        b = Double.parseDouble(num2.getText().toString());

        c = a + b;

        Button btcalc = (Button) findViewById(R.id.btcalc);
        btcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(String.valueOf(c));
            }
        });

        Button backmain = (Button) findViewById(R.id.backmain);
        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BmiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
