package com.yusuf.bukutamu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout login;
    private TextView tvDate;
    private Button form;
    private Calendar calendar;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = (TextView) findViewById(R.id.tvDate);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                                MainActivity.this, R.style.BottomSheetDialogTheme
                        );

                        View bottomSheetLogin = LayoutInflater.from(getApplicationContext())
                                        .inflate(R.layout.activity_login,
                                                (LinearLayout) findViewById(R.id.bottomSheetLogin)
                                        );
                        EditText etUsername = bottomSheetLogin.findViewById(R.id.edt_username);
                        EditText etPassword = bottomSheetLogin.findViewById(R.id.edt_password);
                        Button masuk = bottomSheetLogin.findViewById(R.id.btn_masuk);

                        masuk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String username = etUsername.getText().toString();
                                final String password = etPassword.getText().toString();

                                class Login extends AsyncTask<Void, Void, String>{

                                    ProgressDialog loading;

                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        loading = ProgressDialog.show(MainActivity.this,
                                                "Login...", "Mohon Tunggu...", false, false);
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
                                        super.onPostExecute(s);
                                        loading.dismiss();
                                        if(s.equals("Proceed")){
                                            Intent intent = new Intent(MainActivity.this, LihatTamuActivity.class);
                                            intent.putExtra("username", username);
                                            intent.putExtra("password", password);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                                            etPassword.setText("");
                                        }
                                    }

                                    @Override
                                    protected String doInBackground(Void... v) {
                                        HashMap<String, String> params = new HashMap<>();
                                        params.put(Konfigurasi.KEY_USERNAME, username);
                                        params.put(Konfigurasi.KEY_PASSWORD, password);

                                        RequestHandler rh = new RequestHandler();
                                        String res = rh.sendPostRequest(Konfigurasi.URL_LOGIN, params);
                                        return res;
                                    }
                                }
                                Login l = new Login();
                                l.execute();
                            }
                        });
                        bottomSheetDialog.setContentView(bottomSheetLogin);
                        bottomSheetDialog.show();
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

                        View bottomSheetForm = LayoutInflater.from(getApplicationContext())
                                .inflate(R.layout.activity_form,
                                        (LinearLayout) findViewById(R.id.bottomSheetForm)
                                );

                        EditText etnamatamu = bottomSheetForm.findViewById(R.id.edt_nama);
                        EditText etinstansi = bottomSheetForm.findViewById(R.id.edt_instansi);
                        EditText etalamat = bottomSheetForm.findViewById(R.id.edt_alamat);
                        EditText ettelepon = bottomSheetForm.findViewById(R.id.edt_telepon);
                        EditText etkeperluan = bottomSheetForm.findViewById(R.id.edt_keperluan);
                        Button kirim = bottomSheetForm.findViewById(R.id.btn_kirim);
                        calendar = Calendar.getInstance();
                        date = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(new Date());
                        tvDate.setText(date);

                        kirim.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String nama_tamu = etnamatamu.getText().toString().trim();
                                final String instansi = etinstansi.getText().toString().trim();
                                final String alamat = etalamat.getText().toString().trim();
                                final String telepon = ettelepon.getText().toString().trim();
                                final String keperluan = etkeperluan.getText().toString().trim();
                                final String tanggal = tvDate.getText().toString().trim();
                                final String status = "Belum diterima";

                                class AddTamu extends AsyncTask<Void, Void, String> {

                                    ProgressDialog loading;

                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        loading = ProgressDialog.show(MainActivity.this,
                                                "Menambahkan...", "Tunggu...", false, false);
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
                                        super.onPostExecute(s);
                                        loading.dismiss();
                                        Toast.makeText(MainActivity.this, s,Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    protected String doInBackground(Void... v) {
                                        HashMap<String, String> params = new HashMap<>();
                                        params.put(Konfigurasi.KEY_NAMA, nama_tamu);
                                        params.put(Konfigurasi.KEY_INSTANSI, instansi);
                                        params.put(Konfigurasi.KEY_ALAMAT, alamat);
                                        params.put(Konfigurasi.KEY_TELEPON, telepon);
                                        params.put(Konfigurasi.KEY_KEPERLUAN, keperluan);
                                        params.put(Konfigurasi.KEY_TANGGAL, tanggal);
                                        params.put(Konfigurasi.KEY_STATUS, status);

                                        RequestHandler rh = new RequestHandler();
                                        String res = rh.sendPostRequest(Konfigurasi.URL_ADD_TAMU, params);
                                        return res;
                                    }
                                }

                                AddTamu at = new AddTamu();
                                at.execute();
                            }
                        });
                        bottomSheetDialog.setContentView(bottomSheetForm);
                        bottomSheetDialog.show();
                    }
                }
        );

    }
}