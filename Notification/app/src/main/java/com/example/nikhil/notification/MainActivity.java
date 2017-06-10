package com.example.nikhil.notification;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button notificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationButton = (Button) findViewById(R.id.notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification(MainActivity.this);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void showNotification(Context context) {
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle("New Notification!")
                .setContentText("This is a sample notification!")
                .setAutoCancel(true)
                .setContentIntent(contentIntent(context))
                .setColor(context.getColor(R.color.colorAccent))
                .setSmallIcon(R.drawable.bell);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(332,notificationBuilder.build());
    }

    public static PendingIntent contentIntent(Context context) {

        Intent intent = new Intent(context,MainActivity.class);
        return PendingIntent.getActivity(context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
