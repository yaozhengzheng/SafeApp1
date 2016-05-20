package com.edu.feicui.safeapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edu.feicui.safeapp.R;


/**
 * Created by 16245 on 2016/05/19.
 */
public class SettingItemView extends RelativeLayout{
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.itheima52.mobilesafe";
    private TextView tvTitle;
    private TextView tvDesc;
    private CheckBox cbStatus;
    private String mTitle;
    private String mDescOn;
    private String mDescOff;
    public SettingItemView(Context context) {
        super(context);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTitle = attrs.getAttributeValue(NAMESPACE, "title");// 根据属性名称,获取属性的值
        mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
        mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");

        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View.inflate(getContext(),R.layout.activity_setting_item_view,this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
        cbStatus = (CheckBox) findViewById(R.id.cb_status);
        setTitle(mTitle);// 设置标题
    }

    private void setTitle(String title) {
        tvTitle.setText(title);
    }
    public void setDesc(String desc) {
        tvDesc.setText(desc);
    }
    public boolean isChecked() {
        return cbStatus.isChecked();
    }
    public void setChecked(boolean check) {
        cbStatus.setChecked(check);

        // 根据选择的状态,更新文本描述
        if (check) {
            setDesc(mDescOn);
        } else {
            setDesc(mDescOff);
        }
    }
}
