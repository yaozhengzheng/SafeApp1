package com.edu.feicui.safeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.edu.feicui.safeapp.ui.MainActivity;
import com.edu.feicui.safeapp.ui.SplashActivity;

public class DhActivity extends AppCompatActivity {

    private ImageView img_dh;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh);
        img_dh = (ImageView) findViewById(R.id.iv_dh);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_dh);
        animation.setAnimationListener(mAnimationListener);
// logo 图像控件开始动画
        img_dh.startAnimation(animation);
    }
    private Animation.AnimationListener mAnimationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(DhActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
