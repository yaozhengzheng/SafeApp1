package com.edu.feicui.safeapp.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.feicui.safeapp.R;
import com.edu.feicui.safeapp.activity.RjglActivity;
import com.edu.feicui.safeapp.bean.HomeBean;

import java.util.ArrayList;

/**
 * Created by 16245 on 2016/05/09.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final int SJFD =0;
    public static final int TXWS =1;
    public static final int RJGL =2;
    public static final int JCGL =3;
    public static final int HCQL =4;
    public static final int SZZX =5;
    private GridView mGridView;


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
        switch(position){
            case SJFD:
                Toast.makeText(MainActivity.this,"点击了"+position,
                        Toast.LENGTH_SHORT).show();
                break;
            case TXWS:
                Toast.makeText(MainActivity.this,"点击了"+position,
                        Toast.LENGTH_SHORT).show();
                
                break;
            case RJGL:
                Toast.makeText(MainActivity.this,"点击了"+position,
                        Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, RjglActivity.class);
                startActivity(intent3);
                break;
            case JCGL:
                Toast.makeText(MainActivity.this,"点击了"+position,
                        Toast.LENGTH_SHORT).show();
                break;
            case HCQL:
                Toast.makeText(MainActivity.this,"点击了"+position,
                        Toast.LENGTH_SHORT).show();
                break;
            case SZZX:
                Toast.makeText(MainActivity.this,"点击了"+position,
                        Toast.LENGTH_SHORT).show();

                break;

        }
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
                convertView = View.inflate(MainActivity.this,R.layout.item_grid_list,null);
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
