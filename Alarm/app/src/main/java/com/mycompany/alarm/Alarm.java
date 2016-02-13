package com.mycompany.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import android.app.Activity;
import android.widget.TextView;
import android.widget.TimePicker;
import android.os.Bundle;

public class Alarm extends AppCompatActivity {
    private Intent i;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm);
        i = new Intent(this, AlarmSound.class);
        c = this;

        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setHour(12);
        timePicker.setMinute(15);

        updateDisplay(12, 15);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                updateDisplay(hourOfDay, minute);
                Calendar t = Calendar.getInstance();
                PendingIntent pending = PendingIntent.getActivity(c, 1235, i, PendingIntent.FLAG_CANCEL_CURRENT);
                t.add(Calendar.SECOND, 15);
                AlarmManager alarm = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                Calendar calNow = Calendar.getInstance();
                Calendar calSet = (Calendar) calNow.clone();

                calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calSet.set(Calendar.MINUTE, minute);
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

                if(calSet.compareTo(calNow) <= 0){
                    //Today Set time passed, count to tomorrow
                    calSet.add(Calendar.DATE, 1);
                }
                Log.e(Integer.toString(calSet.HOUR_OF_DAY), Integer.toString(Calendar.HOUR_OF_DAY));
                Log.e(Integer.toString(calSet.MINUTE), Integer.toString(Calendar.MINUTE));

                setAlarm(calSet);
               // calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                // calendar.set(Calendar.MINUTE, minute);
                //Log.e(Integer.toString(Calendar.HOUR_OF_DAY), Integer.toString(Calendar.HOUR_OF_DAY));
               // Log.e(Integer.toString(Calendar.MINUTE), Integer.toString(Calendar.MINUTE));
               //t = calendar;
                //Log.e(calendar.getTime().toString(),"HI");
                //alarm.set(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), pending);

            }
        });
    }
    private void setAlarm(Calendar targetCal){
        Intent intent = new Intent(getBaseContext(), Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1235, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }
    private void updateDisplay(int hourOfDay, int minute) {
        Log.e(Integer.toString(hourOfDay) + " and " + Integer.toString(minute),"HI");
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
