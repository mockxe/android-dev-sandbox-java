package io.mockxe.sandbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class ShareActivity extends AppCompatActivity {

    private TextInputEditText edtTxtMsg;
    private TextInputEditText edtTxtMimeType;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        edtTxtMsg = findViewById(R.id.edtTxtMsg);
        edtTxtMimeType = findViewById(R.id.edtTxtMimeType);
        btnShare = findViewById(R.id.btnShare);

        btnShare.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_SEND)
                    .putExtra(Intent.EXTRA_TEXT, edtTxtMsg.getText().toString())
                    .setType(edtTxtMimeType.getText().toString())
            );
        });

    }
}