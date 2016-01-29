package com.radiusnetworks.ibeaconreference.old;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.radiusnetworks.ibeaconreference.MainActivity;
import com.radiusnetworks.ibeaconreference.R;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 20/1/16.
 */
public class BeaconNotificationManager {

  private Context appContext;

  public BeaconNotificationManager(Context applicationContext) {
    this.appContext = applicationContext;
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public void showNotification(String s) {

    NotificationCompat.Builder builder =
        new NotificationCompat.Builder(appContext)
            .setContentTitle("Beacon Enter")
            .setContentText(s)
            .setSmallIcon(R.drawable.ic_launcher);

    TaskStackBuilder stackBuilder = TaskStackBuilder.create(appContext);
    stackBuilder.addNextIntent(new Intent(appContext, MainActivity.class));
    PendingIntent resultPendingIntent =
        stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        );
    builder.setContentIntent(resultPendingIntent);
    NotificationManager notificationManager =
        (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(1, builder.build());


  }
}
