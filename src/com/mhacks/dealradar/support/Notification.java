package com.mhacks.dealradar.support;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.mhacks.dealradar.DealRadar;
import com.mhacks.dealradar.R;

/**
 * Created by Niraj Venkat on 9/21/13.
 */

public class Notification {

    private Context context;
    private int mId;
    private String title, text, objectId;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private PendingIntent resultPendingIntent;

    public Notification(Context context, int mId, String title, String text, String objectId) {
        this.context = context;
        this.mId = mId;
        this.title = title;
        this.text = text;
        this.objectId = objectId;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public void pushNotification() {
        mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, DealRadar.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(DealRadar.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(mId, mBuilder.build());
    }

    public void remove()
    {
        mNotificationManager.cancel(mId);
    }
}
