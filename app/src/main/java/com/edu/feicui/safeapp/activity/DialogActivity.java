package com.edu.feicui.safeapp.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.feicui.safeapp.R;

public class DialogActivity extends Activity implements View.OnClickListener {
    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mPref = getSharedPreferences("config", MODE_PRIVATE);
        Button btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        showPasswordSetDialog();
    }

    private void showPasswordDialog() {
        // 判断是否设置密码
        String savedPassword = mPref.getString("password", null);
        if (!TextUtils.isEmpty(savedPassword)) {
            // 输入密码弹窗
            showPasswordInputDialog();
        } else {
            // 如果没有设置过, 弹出设置密码的弹窗
            showPasswordSetDialog();
        }
    }

    /**
     * 输入密码弹窗
     */
    private void showPasswordInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        //将自己定义的布局文件设置给dialog
        View view = View.inflate(this, R.layout.dialog_input_passedword, null);
        dialog.setView(view);
        final EditText etpassword = (EditText) findViewById(R.id.et_psw);
        final EditText etpasswordagain = (EditText) findViewById(R.id.et_psw_agin);
        Button btn1 = (Button) findViewById(R.id.but1);
        Button btn2 = (Button) findViewById(R.id.but2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /**
     * 设置密码的弹窗
     */
    private void showPasswordSetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_set_passedword, null);
        dialog.setView(view);
        dialog.show();
        final EditText etpassword = (EditText) findViewById(R.id.et_psw);
        final EditText etpasswordagain = (EditText) findViewById(R.id.et_psw_agin);
        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etpassword.getText().toString();
                String passwordagain = etpasswordagain.getText().toString();
                if (!TextUtils.isEmpty(password)&&!passwordagain.isEmpty()){
                    if (password.equals(passwordagain)){
                        Toast.makeText(DialogActivity.this,"登陆成功！",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(DialogActivity.this,"两次的内容不一致！！",
                                Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(DialogActivity.this,"输入框内容不能为空",
                            Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏弹窗
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
