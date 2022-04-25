package io.mockxe.sandbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnActivityShare;
    private Button btnActivityPermissionCamera;
    private Button btnActivityAlarm;
    private Button btnActivityDatePicker;
    private Button btnActivityFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivityShare = findViewById(R.id.btnActivityShare);
        btnActivityPermissionCamera = findViewById(R.id.btnActivityPermissionCamera);
        btnActivityAlarm = findViewById(R.id.btnActivityAlarm);
        btnActivityDatePicker = findViewById(R.id.btnActivityDatePicker);
        btnActivityFragments = findViewById(R.id.btnActivityFragments);


        btnActivityShare.setOnClickListener(v -> {
            startNewActivity(ShareActivity.class);
        });

        btnActivityPermissionCamera.setOnClickListener(v -> {
            startNewActivity(PermissionCameraActivity.class);
        });

        btnActivityAlarm.setOnClickListener(v -> {
            startNewActivity(AlarmActivity.class);
        });

        btnActivityDatePicker.setOnClickListener(v -> {
            startNewActivity(DatePickerActivity.class);
        });

        btnActivityFragments.setOnClickListener(v -> {
            startNewActivity(FragmentActivity.class);
        });

    }

    private void startNewActivity(Class<?> clazz) {
        startActivity(new Intent(MainActivity.this, clazz));
    }

}