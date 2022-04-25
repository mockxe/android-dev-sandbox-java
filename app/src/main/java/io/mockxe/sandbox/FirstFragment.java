package io.mockxe.sandbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class FirstFragment extends Fragment {

    private TextView txtName;
    private TextInputEditText edtTxtName;
    private Button btnSend;

    private SendNameInterface sendNameInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        txtName = view.findViewById(R.id.txtName);
        edtTxtName = view.findViewById(R.id.edtTxtName);
        btnSend = view.findViewById(R.id.btnSend);

        try {
            sendNameInterface = (SendNameInterface) getActivity();
        } catch (ClassCastException e) {
            sendNameInterface = null;
            e.printStackTrace();
        }

        btnSend.setOnClickListener(v -> {
            if (sendNameInterface != null) {
                sendNameInterface.sendNameResult(edtTxtName.getText().toString());
            }
        });

        Bundle args =  getArguments();
        if (args != null) {
            String name = args.getString("name");
            if (name != null) {
                txtName.setText(name);
            }
        }

        return view;
    }

}
