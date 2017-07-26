package example.com.smu_4_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.PopupMenu;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Pattern;

import static example.com.smu_4_demo.R.id.parent;


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

        final String[] code = new String[1];
        final EditText nameInput = (EditText) findViewById(R.id.name);
        final EditText codeInput = (EditText) findViewById(R.id.code);
        nameInput.setFilters(new InputFilter[]{filterKorAlpha});
        final Button saveButton = (Button) findViewById(R.id.savebutton);
        Button catebt = (Button) findViewById(R.id.catebutton);

        saveButton.setEnabled(false);

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
                Toast.makeText(getApplication(), "이름을 지웠습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        clearbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeInput.setText("");
                Toast.makeText(getApplication(), "학번을 지웠습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Button conformbt = (Button) findViewById(R.id.conformbt);
        conformbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = nameInput.getText().toString();
                String code1 = codeInput.getText().toString();
                String code = null;

                try {
                    code = codeInput.getText().toString().trim();

                    int rt = Integer.parseInt(code);
                    Toast.makeText(getApplicationContext(), "학번 : " + rt, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "학번에는 숫자만 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                if(code1 != null && name1 != null){
                    saveButton.setEnabled(true);
                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                //String code = codeInput.getText().toString();
                code[0] = codeInput.getText().toString().trim();

                mNM.notify(777, mNoti);

                SharedPreferences pref = getPref(AddViewActivity.this);
                pref.edit().putString(name, code[0]).apply();
                finish();
            }
        });
    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.code_cate, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Button catebt = (Button) findViewById(R.id.catebutton);
                catebt.setText(item.getTitle());
                Toast.makeText(getApplication(), item.getTitle() + "을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
    }

    public InputFilter filterKorAlpha = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[ㄱ-ㅎ가-흐a-zA-Z]*$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };





}