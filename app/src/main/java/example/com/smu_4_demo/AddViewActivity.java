package example.com.smu_4_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddViewActivity extends AppCompatActivity {

    private NotificationManager mNM;
    private Notification mNoti;

    public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);

        ImageButton clearbt = (ImageButton) findViewById(R.id.clearbt);
        ImageButton clearbt2 = (ImageButton) findViewById(R.id.clearbt2);

        final EditText nameInput = (EditText) findViewById(R.id.name);
        final EditText codeInput = (EditText) findViewById(R.id.code);
        Button saveButton = (Button) findViewById(R.id.savebutton);

        mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(AddViewActivity.this, List_ViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(AddViewActivity.this, 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNoti = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("저장완료")
                .setContentText("리스트 확인")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setTicker("알림!")
                .setContentIntent(pendingIntent)
                .build();

        clearbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput.setText("");
            }
        });

        clearbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeInput.setText("");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String code = codeInput.getText().toString();
                
                mNM.notify(777, mNoti);

                SharedPreferences pref = getPref(AddViewActivity.this);
                pref.edit().putString(name, code).apply();
                finish();
            }
        });
    }
    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
    }
}