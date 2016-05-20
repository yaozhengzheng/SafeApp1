package com.edu.feicui.safeapp.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.edu.feicui.safeapp.R;
import com.edu.feicui.safeapp.view.SettingItemView;

public class SettingActivity extends AppCompatActivity {
    private SettingItemView sivUpdate;// 设置升级
    private SharedPreferences mPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mPref = getSharedPreferences("config", MODE_PRIVATE);
        sivUpdate = (SettingItemView) findViewById(R.id.siv_update);
        boolean autoUpdate = mPref.getBoolean("auto_update", true);
        if (autoUpdate) {
            // sivUpdate.setDesc("自动更新已开启");
            sivUpdate.setChecked(true);
        } else {
            // sivUpdate.setDesc("自动更新已关闭");
            sivUpdate.setChecked(false);
        }
        sivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断当前的勾选状态
                if (sivUpdate.isChecked()) {
                    // 设置不勾选
                    sivUpdate.setChecked(false);
                    // sivUpdate.setDesc("自动更新已关闭");
                    // 更新sp
                    mPref.edit().putBoolean("auto_update", false).commit();
                } else {
                    sivUpdate.setChecked(true);
                    // sivUpdate.setDesc("自动更新已开启");
                    // 更新sp
                    mPref.edit().putBoolean("auto_update", true).commit();
                }
            }
        });
    }
}
