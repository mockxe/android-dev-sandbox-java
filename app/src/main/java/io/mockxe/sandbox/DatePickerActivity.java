package io.mockxe.sandbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {

    private Calendar calendar = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            txtDate.setText(date);

        }
    };

    private String date;

    private TextView txtDate;
    private Button btnDatePickerPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        txtDate = findViewById(R.id.txtDate);
        btnDatePickerPicker = findViewById(R.id.btnDatePicker);

        txtDate.setText("");

        if (savedInstanceState != null) {
            date = savedInstanceState.getString("date");
            if (date != null && !date.equals("")) {
                txtDate.setText(date);
            }
        }


        btnDatePickerPicker.setOnClickListener(v -> {

            new DatePickerDialog(
                    this,
                    onDateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            ).show();

        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (date != null && !date.equals("")) {
            outState.putString("date", date);
        }

    }
}