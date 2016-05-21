package com.edu.feicui.safeapp.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.feicui.safeapp.R;
import com.edu.feicui.safeapp.activity.RjglActivity;
import com.edu.feicui.safeapp.activity.SettingActivity;
import com.edu.feicui.safeapp.activity.SjfdActivity;
import com.edu.feicui.safeapp.activity.SzzxActivity;
import com.edu.feicui.safeapp.bean.HomeBean;
import com.edu.feicui.safeapp.util.MD5Utils;

import java.util.ArrayList;

/**tr
 * Created by 16245 on 2016/05/09.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final int SJFD = 0;
    public static final int TXWS = 1;
    public static final int RJGL = 2;
    public static final int JCGL = 3;
    public static final int HCQL = 4;
    public static final int SZZX = 5;
    private GridView mGridView;

    private SharedPreferences mPref;
    private ArrayList<HomeBean> mDatas;

    private String[] desc = {"手机防盗", "通讯卫士",
            "软件管理", "进程管理",
            "缓存清理", "设置中心"};

    private int[] icons = {R.drawable.icon_phonemgr, R.drawable.icon_telmgr,
            R.drawable.icon_softmgr, R.drawable.icon_rocket,
            R.drawable.icon_sdclean, R.drawable.icon_filemgr};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化数据
        initData();

        mPref = getSharedPreferences("config", MODE_PRIVATE);
        mGridView = (GridView) findViewById(R.id.gv_home);
        mGridView.setAdapter(new HomeAdapter());
        mGridView.setOnItemClickListener(this);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            HomeBean bean = new HomeBean();
            bean.pic = icons[i];
            bean.desc = desc[i];
            mDatas.add(bean);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case SJFD:
                showPasswordDialog();
                break;
            case TXWS:

                break;
            case RJGL:
                startActivity(new Intent(this,RjglActivity.class));
            break;
            case JCGL:

                break;
            case HCQL:

                break;
            case SZZX:
                startActivity(new Intent(this,SettingActivity.class));
                break;

        }
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
     * 设置密码的弹窗
     */
    private void showPasswordSetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this,
                R.layout.dialog_set_passedword, null);
        dialog.setView(view);
        final EditText etPassword = (EditText) view.findViewById(R.id.et_psw);
        final EditText etPasswordagain = (EditText) view.findViewById(R.id.et_psw_agin);
        Button btnOK = (Button) view.findViewById(R.id.button1);
        Button btnCancel = (Button) view.findViewById(R.id.button2);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String passwordagain = etPasswordagain.getText().toString();
                if (!TextUtils.isEmpty(password) && !passwordagain.isEmpty()) {
                    if (password.equals(passwordagain)) {
                        Toast.makeText(MainActivity.this, "登录成功!",
                                Toast.LENGTH_SHORT).show();
                        // 将密码保存起来
                        mPref.edit().putString("password", MD5Utils.encode(password)).commit();
                        Toast.makeText(MainActivity.this,
                                "密码保存成功", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        // 跳转到手机防盗页面
                        startActivity(new Intent(MainActivity.this, SjfdActivity.class));

                    } else {
                        Toast.makeText(MainActivity.this, "两次密码不一致!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "输入内容不能为空!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();// 隐藏dialog
            }
        });
        dialog.show();
    }

    /**
     * 输入密码弹窗
     */
    private void showPasswordInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_input_passedword, null);
        dialog.setView(view);
        final EditText etPassword = (EditText) view.findViewById(R.id.et_psw);
        Button btnok = (Button) view.findViewById(R.id.but1);
        Button btncancel = (Button) view.findViewById(R.id.but2);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                if (!TextUtils.isEmpty(password)) {
                    String savedPassword = mPref.getString("password", null);
                    if (MD5Utils.encode(password).equals(savedPassword)) {
                        dialog.dismiss();
                        //跳转到手机防盗页面
                        startActivity(new Intent(MainActivity.this, SjfdActivity.class));
                    } else {
                        Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "输入框内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private class HomeAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null) {
                return mDatas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_grid_list, null);
            }
            ImageView ivIcons = (ImageView) convertView.findViewById(R.id.item_iv_pic);
            TextView tvDesc = (TextView) convertView.findViewById(R.id.item_tv_desc);

            HomeBean bean = mDatas.get(position);
            ivIcons.setImageResource(bean.pic);

            tvDesc.setText(bean.desc);
            return convertView;
        }
    }
}
