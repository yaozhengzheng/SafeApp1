package com.edu.feicui.safeapp.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.feicui.safeapp.R;

public class SzzxActivity extends AppCompatActivity implements View.OnClickListener {
    private View layout_battery; // 电池电量整体布局
    private TextView tv_battery; // 电池电量百分比
    private ProgressBar pb_battery;// 电池电量进度
    private ProgressBar pb_loading;
    private int currentBattery; // 当前电量
    private int temperatureBattery;// 电池温度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szzx);
        initMainButton();
    }

    private void initMainButton() {
        layout_battery=findViewById(R.id.ll_layout_battery);
        layout_battery.setOnClickListener(this);
        tv_battery= (TextView) findViewById(R.id.tv_battery);
        pb_battery= (ProgressBar) findViewById(R.id.pb_battery);
        pb_loading= (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        showBatteryMessage();

    }

    private void showBatteryMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("电池电量信息");
        builder.setItems(new String[] { "当前电量：" + currentBattery,
                "电池温度：" + temperatureBattery }, null);
        builder.show();
    }

}
