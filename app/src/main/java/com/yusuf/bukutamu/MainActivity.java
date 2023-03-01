package com.yusuf.bukutamu;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    LinearLayout login;
    Button form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btn_login);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                                MainActivity.this, R.style.BottomSheetDialogTheme
                        );
                        bottomSheetDialog.setContentView(R.layout.activity_login);

                        EditText namatamu = findViewById(R.id.edt_nama);
                        EditText instansi = findViewById(R.id.edt_instansi);
                        EditText alamat = findViewById(R.id.edt_alamat);
                        EditText telepon = findViewById(R.id.edt_telepon);
                        EditText keperluan = findViewById(R.id.edt_keperluan);

                        bottomSheetDialog.show();
                        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                            }});
                    }
                }
        );

        form = findViewById(R.id.btn_form);
        form.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                                MainActivity.this, R.style.BottomSheetDialogTheme
                        );
                        bottomSheetDialog.setContentView(R.layout.activity_form);

                        EditText username = findViewById(R.id.edt_username);
                        EditText password = findViewById(R.id.edt_password);

                        bottomSheetDialog.show();
                        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                            }});
                    }
                }
        );
    }
}