package io.mockxe.sandbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FragmentActivity extends AppCompatActivity implements SendNameInterface{

    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        txtName = findViewById(R.id.txtName);

        Bundle args = new Bundle();
        args.putString("name", "mockxe");

        Fragment firstFragment = new FirstFragment();
        firstFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, firstFragment)
                .commit();

    }

    @Override
    public void sendNameResult(String name) {
        txtName.setText(name);
    }


}