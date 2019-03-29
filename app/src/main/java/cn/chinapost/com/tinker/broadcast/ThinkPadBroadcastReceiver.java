package cn.chinapost.com.tinker.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by thinkpad on 2019/3/28.
 */

public class ThinkPadBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "郑诗雅", Toast.LENGTH_SHORT).show();
    }
}
