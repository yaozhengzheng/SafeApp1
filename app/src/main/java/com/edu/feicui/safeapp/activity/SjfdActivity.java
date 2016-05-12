package com.edu.feicui.safeapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.edu.feicui.safeapp.R;

public class SjfdActivity extends AppCompatActivity {
    private SharedPreferences mPrefs;
    private TextView tvSafePhone;
    private ImageView ivProtect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs=getSharedPreferences("config",MODE_PRIVATE);
        //判断是否进入设置向导
        boolean configed=mPrefs.getBoolean("configed",true);
        if (configed){
            setContentView(R.layout.activity_sjfd);
            // 根据sp更新安全号码
            tvSafePhone = (TextView) findViewById(R.id.tv_safe_phone);
            String phone = mPrefs.getString("safe_phone", "");
            tvSafePhone.setText(phone);
            // 根据sp更新保护锁
            ivProtect = (ImageView) findViewById(R.id.iv_protect);
            boolean protect = mPrefs.getBoolean("protect", false);
            if (protect) {
                ivProtect.setImageResource(R.drawable.lock);
            } else {
                ivProtect.setImageResource(R.drawable.unlock);
            }
        }else{
            //跳转设置向导页
            startActivity(new Intent(this,Setup1Activity.class));
            finish();
        }

    }
    /**
     * 重新进入设置向导
     */
    public void reEnter(View view){
        startActivity(new Intent(this,Setup1Activity.class));
        finish();
    }
}
