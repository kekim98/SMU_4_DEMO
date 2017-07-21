package example.com.smu_4_demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;

/**
 * Created by gold24park on 2016. 8. 17..
 */
public class IntroActivity extends Activity {
    private Handler handler;
    private ConstraintLayout mLayout;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mLayout = (ConstraintLayout) findViewById(R.id.mLayout);
        mLayout.setBackgroundColor(Color.rgb(255,255,255));
        init();

        handler.postDelayed(runnable, 4000);
    }

    public void init() {
        handler = new Handler();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}

