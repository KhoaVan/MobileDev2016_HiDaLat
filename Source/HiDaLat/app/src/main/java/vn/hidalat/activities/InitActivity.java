package vn.hidalat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import vn.hidalat.MainActivity;
import vn.hidalat.R;
import vn.hidalat.utils.constant.Const;

public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        Log.e("init", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(InitActivity.this, MainActivity.class));
            }
        }, Const.UI_DELAY);
    }
}
