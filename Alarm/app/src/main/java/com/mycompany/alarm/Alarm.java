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

public class Alarm extends AppCompatActivity {
    private Intent i;
    private Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alarm);

        i = new Intent(this, AlarmSound.class);
        c = this;

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
         timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar t = Calendar.getInstance();
                PendingIntent pending = PendingIntent.getActivity(c, 1235, i, PendingIntent.FLAG_CANCEL_CURRENT);
                t.add(Calendar.SECOND, 15);
                AlarmManager alarm = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                Log.e(Integer.toString(Calendar.HOUR_OF_DAY), Integer.toString(Calendar.HOUR_OF_DAY));
                Log.e(Integer.toString(Calendar.MINUTE), Integer.toString(Calendar.MINUTE));

                alarm.set(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), pending);

            }
        });
        //startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle actiond bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
