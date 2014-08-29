package com.example.test;
 
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
 
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;
public class GcmIntentService extends GCMBaseIntentService {
 
private static void generateNotification(Context context, String message) {
 
int icon = R.drawable.ic_launcher;
long when = System.currentTimeMillis();
 
 
NotificationManager notificationManager = (NotificationManager) context
.getSystemService(Context.NOTIFICATION_SERVICE);
 
Notification notification = new Notification(icon, message, when);
 
String title = context.getString(R.string.app_name);
 
Intent notificationIntent = new Intent(context, MainActivity.class);
 
notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP 
        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
PendingIntent intent = PendingIntent.getActivity(context, 0,
notificationIntent, 0);
 
 
 
notification.setLatestEventInfo(context, title, message, intent);
 
notification.flags |= Notification.FLAG_AUTO_CANCEL;
 
notificationManager.notify(0, notification);
 
}
 
@Override
protected void onError(Context arg0, String arg1) {
 
}
 
@Override
protected void onMessage(Context context, Intent intent) {
 
String msg = intent.getStringExtra("test");
Log.e("getmessage", "getmessage:" + msg);
generateNotification(context,msg);
//Toast.makeText(context, intent.getExtras().getString("test"),Toast.LENGTH_SHORT).show();
}
 
 
 
@Override
 
protected void onRegistered(Context context, String reg_id) {
Log.e("Ű�� ����մϴ�.(GCM INTENTSERVICE)", reg_id);
}
 
 
 
@Override
 
protected void onUnregistered(Context arg0, String arg1) {
Log.e("Ű�� �����մϴ�.(GCM INTENTSERVICE)","���ŵǾ����ϴ�.");
}
 
}