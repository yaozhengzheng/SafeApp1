package com.edu.feicui.safeapp.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.edu.feicui.safeapp.R;

public class Setup1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);
    }
    public void next(View view){
        startActivity(new Intent(this,Setup2Activity.class));
        finish();
        //两个界面切换动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }
}
