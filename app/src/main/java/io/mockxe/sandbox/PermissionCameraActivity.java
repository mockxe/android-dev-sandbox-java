package io.mockxe.sandbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class PermissionCameraActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 101;
    private static final int CAMERA_INTENT_CODE = 201;
    private static final int SETTING_INTENT_CODE = 202;

    private ConstraintLayout parent;
    private Button btnClick;
    private ImageView imgCamera;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_camera);

        parent = findViewById(R.id.activityMain);
        btnClick = findViewById(R.id.btnClick);
        imgCamera = findViewById(R.id.imgCamera);

        btnClick.setOnClickListener(v -> handlePermission());


        if (savedInstanceState != null) {
            byte[] byteArray = savedInstanceState.getByteArray("imgCamera");

            if (byteArray != null && byteArray.length > 0) {
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                imgCamera.setImageBitmap(bitmap);
            }
        }

    }

    private void handlePermission() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startActivityForResult(
                    new Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                    CAMERA_INTENT_CODE);
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                Snackbar.make(parent, "This application needs camera permission", Snackbar.LENGTH_INDEFINITE)
                        .setAction("grant permission", v1 -> openPermissions())
                        .show();
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            }
        }
    }

    private void openPermissions() {
        startActivityForResult(
                new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .setData(Uri.parse("package:" + getPackageName())),
                SETTING_INTENT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case CAMERA_INTENT_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        bitmap = (Bitmap) bundle.get("data");
                        imgCamera.setImageBitmap(bitmap);
                    }
                }

                break;

            case SETTING_INTENT_CODE:
                handlePermission();
                break;

            default:

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(
                            new Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                            CAMERA_INTENT_CODE);
                } else {
                    Snackbar.make(parent, "camera permission denied", Snackbar.LENGTH_SHORT)
                            .show();
                }

                break;

            default:
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (bitmap != null) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            outState.putByteArray("imgCamera", byteArray);

        }

    }
}