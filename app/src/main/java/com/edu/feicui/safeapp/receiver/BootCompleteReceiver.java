package com.edu.feicui.safeapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 监听手机开机启动的广播
 * Created by 16245 on 2016/05/19.
 */
public class BootCompleteReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        boolean protect=sp.getBoolean("protect",false);
        //只有在防盗开启的时候才能进行sim卡判断
        if (protect){
            //获取绑定的sim卡
            String sim=sp.getString("sim",null);
            if (!TextUtils.isEmpty(sim)){
                //获取当前手机的sim
                TelephonyManager tm= (TelephonyManager)
                        context.getSystemService(Context.TELEPHONY_SERVICE);
                //拿到当前手机的sim卡
                String currentSim=tm.getSimSerialNumber()+"111";
                if (sim.equals(currentSim)){
                    System.out.println("手机安全");
                }else{
                    System.out.println("手机sim卡已更换，立即发送报警短信！");
                    //读取安全号码
                    String phone=sp.getString("safe_phone","");
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(phone,null,"sim card changed",null,null);
                }
            }
        }

    }
}
