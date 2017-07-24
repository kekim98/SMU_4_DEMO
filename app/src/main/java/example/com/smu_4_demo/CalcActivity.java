package example.com.smu_4_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

    Double a, b, c=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        final EditText etnum1 = (EditText) findViewById(R.id.etnum1);
        final EditText etnum2 = (EditText) findViewById(R.id.etnum2);
        final TextView res = (TextView) findViewById(R.id.res);

        Button btsum = (Button) findViewById(R.id.btsum);
        btsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Double.parseDouble(etnum1.getText().toString());
                b = Double.parseDouble(etnum2.getText().toString());

                c = a + b;
                res.setText(String.valueOf(c));
            }
        });

        Button btsub = (Button) findViewById(R.id.btsub);
        btsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Double.parseDouble(etnum1.getText().toString());
                b = Double.parseDouble(etnum2.getText().toString());

                c = a - b;
                res.setText(String.valueOf(c));
            }
        });

        Button btmul = (Button) findViewById(R.id.btmul);
        btmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Double.parseDouble(etnum1.getText().toString());
                b = Double.parseDouble(etnum2.getText().toString());

                c = a * b;
                res.setText(String.valueOf(c));
            }
        });

        Button btdiv = (Button) findViewById(R.id.btdiv);
        btdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Double.parseDouble(etnum1.getText().toString());
                b = Double.parseDouble(etnum2.getText().toString());

                c = a / b;
                res.setText(String.valueOf(c));
            }
        });

        Button btback = (Button) findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalcActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
