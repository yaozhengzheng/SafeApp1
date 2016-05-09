package com.edu.feicui.safeapp.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.feicui.safeapp.MainActivity;
import com.edu.feicui.safeapp.R;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ImageView[] icons;



    /** 初始化引导图片 */
    private void initLeadIcon() {
        icons[0] = (ImageView) findViewById(R.id.icon1);
        icons[1] = (ImageView) findViewById(R.id.icon2);
        icons[2] = (ImageView) findViewById(R.id.icon3);
        icons[0].setImageResource(R.drawable.adware_style_selected);

    }

    private ViewPager mViewPager;
    private ArrayList<View> mList;
    int[]pics={
            R.mipmap.adware_style_applist,
            R.mipmap.adware_style_creditswall,
            R.mipmap.adware_style_banner};
    private Button mBtnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initview();
    }

    private void initview() {
       mBtnSkip= (Button) findViewById(R.id.btn_skip);
        mBtnSkip.setOnClickListener(this);
        mList=new ArrayList<>();
        mViewPager=(ViewPager) findViewById(R.id.vp_guide);
        for (int i = 0; i <pics.length ; i++) {
            ImageView iv=new ImageView(this);
            iv.setImageResource(pics[i]);
            mList.add(iv);
        }
        mViewPager.setAdapter(new MyPagerAdapter(mList));
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
    }
//按钮的跳转方法
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
//正在滚动的时候调用的方法，反复调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position== pics.length-1){
            mBtnSkip.setVisibility(View.VISIBLE);
        }
        else{
            mBtnSkip.setVisibility(View.INVISIBLE);
        }

    }
//当viewpager滚动的时候调用的第一个方法
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    // 官方提供的动画1
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    private class MyPagerAdapter extends PagerAdapter{
        private ArrayList<View> mList;

        public MyPagerAdapter(ArrayList<View> list) {
        mList=list;
        }
        // 初始化position 展现到界面上来
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position),0);
            return mList.get(position);
        }
        // 当不可见时 销毁position
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public int getCount() {
            if (mList!=null){
                return mList.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }


}
