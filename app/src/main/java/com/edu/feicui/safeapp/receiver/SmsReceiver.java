package com.edu.feicui.safeapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

/**
 * Created by 16245 on 2016/05/21.
 */
public class SmsReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[]objects= (Object[]) intent.getExtras().get("pdus");
        for(Object object:objects){
            //短信最多140字节，超出的话会分条发送，所以用数组
            //短信内容很短，for只循环一次
            SmsMessage message=SmsMessage.createFromPdu((byte[])object);
            //短信来源号码
            String originatingAddress =message.getOriginatingAddress();
            //短信内容
            String messageBody=message.getMessageBody();
            System.out.println("短信号码是"+originatingAddress+"短信内容是"+messageBody);
        }
    }
}
