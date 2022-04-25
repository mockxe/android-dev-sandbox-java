package io.mockxe.sandbox;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private static final int ALARM_PERMISSION_CODE = 101;

    private TextInputEditText edtTxtHour;
    private TextInputEditText edtTxtMin;
    private TextInputEditText edtTxtAlarmName;
    private CheckBox cbMonday;
    private CheckBox cbTuesday;
    private CheckBox cbWednesday;
    private CheckBox cbThursday;
    private CheckBox cbFriday;
    private CheckBox cbSaturday;
    private CheckBox cbSunday;
    private Button btnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        edtTxtHour = findViewById(R.id.edtTxtHour);
        edtTxtMin = findViewById(R.id.edtTxtMin);
        edtTxtAlarmName = findViewById(R.id.edtTxtAlarmName);
        cbMonday = findViewById(R.id.cbMonday);
        cbTuesday = findViewById(R.id.cbTuesday);
        cbWednesday = findViewById(R.id.cbWednesday);
        cbThursday = findViewById(R.id.cbThursday);
        cbFriday = findViewById(R.id.cbFriday);
        cbSaturday = findViewById(R.id.cbSaturday);
        cbSunday = findViewById(R.id.cbSunday);
        btnAlarm = findViewById(R.id.btnAlarm);

        if (checkSelfPermission(Manifest.permission.SET_ALARM) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.SET_ALARM}, ALARM_PERMISSION_CODE);
        }

        btnAlarm.setOnClickListener(v -> {

            ArrayList<Integer> days = new ArrayList<>();
            if (cbMonday.isChecked())
                days.add(Calendar.MONDAY);
            if (cbTuesday.isChecked())
                days.add(Calendar.TUESDAY);
            if (cbWednesday.isChecked())
                days.add(Calendar.WEDNESDAY);
            if (cbThursday.isChecked())
                days.add(Calendar.THURSDAY);
            if (cbFriday.isChecked())
                days.add(Calendar.FRIDAY);
            if (cbSaturday.isChecked())
                days.add(Calendar.SATURDAY);
            if (cbSunday.isChecked())
                days.add(Calendar.SUNDAY);

            try {
                int hour = Integer.parseInt(edtTxtHour.getText().toString());
                int minute = Integer.parseInt(edtTxtMin.getText().toString());
                String name = edtTxtAlarmName.getText().toString();

                if (
                        hour >= 0 && hour <=23 &&
                        minute >= 0 && minute <= 59
                ) {
                    startActivity(
                            new Intent(AlarmClock.ACTION_SET_ALARM)
                                    .putExtra(AlarmClock.EXTRA_HOUR, hour)
                                    .putExtra(AlarmClock.EXTRA_MINUTES, minute)
                                    .putExtra(AlarmClock.EXTRA_MESSAGE, name)
                                    .putExtra(AlarmClock.EXTRA_DAYS, days)
                    );
                } else {
                    Snackbar.make(v, "invalid time", Snackbar.LENGTH_SHORT)
                            .show();
                }

            } catch (NumberFormatException e) {
                Snackbar.make(v, "invalid number", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

    }
}