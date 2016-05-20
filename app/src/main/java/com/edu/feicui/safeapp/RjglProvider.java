package com.edu.feicui.safeapp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.edu.feicui.safeapp.bean.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16245 on 2016/05/11.
 */
public class RjglProvider {
    /**
     * 自定义一个方法拿到需要的信息
     * 传入当前的上下文
     */
    public static List<AppBean> getAppInfo(Context context) {
        //上下文拿到packagemanager对象
        PackageManager packageManager = context.getPackageManager();
        //通过对象拿到集合
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        //定义一个数据集合
        List<AppBean> mData = new ArrayList<>();
        //遍历packageManager集合，拿到里面的数据
        for (PackageInfo info : packages) {
            //拿到图标
            Drawable icon = info.applicationInfo.loadIcon(packageManager);
            //拿到应用程序的名称
            String name = info.applicationInfo.loadLabel(packageManager).toString();
            //拿到应用程序的包名
            String packageName = info.applicationInfo.packageName;
            AppBean bean = new AppBean();
            bean.name = name;
            bean.icon = icon;
            bean.packageName = packageName;
            mData.add(bean);
        }
        return mData;
    }
}
