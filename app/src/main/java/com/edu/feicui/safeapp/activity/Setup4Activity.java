package com.edu.feicui.safeapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.edu.feicui.safeapp.R;

public class Setup4Activity extends BaseSetupActivity {
    private CheckBox cbProtect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
        cbProtect = (CheckBox) findViewById(R.id.cb_protect);

        boolean protect = mPref.getBoolean("protect", false);
        if (protect) {
            cbProtect.setText("防盗保护已经开启");
            cbProtect.setChecked(true);
        } else {
            cbProtect.setText("防盗保护没有开启");
            cbProtect.setChecked(false);
        }
        //当checkbox发生变化时会调此方法
        cbProtect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbProtect.setText("防盗保护已经开启");
                    mPref.edit().putBoolean("protect", true).commit();
                } else {
                    cbProtect.setText("防盗保护没有开启");
                    mPref.edit().putBoolean("protect", false).commit();
                }
            }
        });
    }

    @Override
    public void showNextPage() {
        startActivity(new Intent(this,SjfdActivity.class));
        finish();
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
        mPref.edit().putBoolean("configed",true).commit();

    }

    @Override
    public void showPreviousPage() {
        startActivity(new Intent(this,Setup3Activity.class));
        finish();
        overridePendingTransition(R.anim.tran_previous_in, R.anim.tran_previous_out);

    }


}
