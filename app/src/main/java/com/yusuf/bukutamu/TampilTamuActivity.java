package com.yusuf.bukutamu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilTamuActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNama, etInstansi, etAlamat, etTelepon, etKeperluan, etStatus;
    private TextView tvTanggal;
    private Button buttonUpdate;
    private Button buttonDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_tamu);

        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.KEY_IDTAMU);

        etNama = (EditText) findViewById(R.id.edt_nama);
        etInstansi = (EditText) findViewById(R.id.edt_instansi);
        etAlamat = (EditText) findViewById(R.id.edt_alamat);
        etTelepon = (EditText) findViewById(R.id.edt_telepon);
        etKeperluan = (EditText) findViewById(R.id.edt_keperluan);
        tvTanggal = (TextView) findViewById(R.id.tv_tanggal);
        etStatus = (EditText) findViewById(R.id.edt_status);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        getTamu();
    }

    private void getTamu(){
        class GetTamu extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilTamuActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showTamu(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_EDIT_TAMU, id);
                return s;
            }
        }
        GetTamu getData = new GetTamu();
        getData.execute();
    }

    private void showTamu(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

            String nama = c.getString(Konfigurasi.TAG_NAMA);
            String instansi = c.getString(Konfigurasi.TAG_INSTANSI);
            String alamat = c.getString(Konfigurasi.TAG_ALAMAT);
            String telepon = c.getString(Konfigurasi.TAG_TELEPON);
            String keperluan = c.getString(Konfigurasi.TAG_KEPERLUAN);
            String tanggal = c.getString(Konfigurasi.TAG_TANGGAL);
            String status = c.getString(Konfigurasi.TAG_STATUS);

            etNama.setText(nama);
            etInstansi.setText(instansi);
            etAlamat.setText(alamat);
            etTelepon.setText(telepon);
            etKeperluan.setText(keperluan);
            tvTanggal.setText(tanggal);
            etStatus.setText(status);

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }


    private void updateTamu(){
        final String nama_tamu = etNama.getText().toString();
        final String instansi = etInstansi.getText().toString();
        final String alamat = etAlamat.getText().toString();
        final String telepon = etTelepon.getText().toString();
        final String keperluan = etKeperluan.getText().toString();
        final String tanggal = tvTanggal.getText().toString();
        final String status = etStatus.getText().toString();

        class UpdateTamu extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilTamuActivity.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilTamuActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_IDTAMU,id);
                hashMap.put(Konfigurasi.KEY_NAMA,nama_tamu);
                hashMap.put(Konfigurasi.KEY_INSTANSI,instansi);
                hashMap.put(Konfigurasi.KEY_ALAMAT,alamat);
                hashMap.put(Konfigurasi.KEY_TELEPON,telepon);
                hashMap.put(Konfigurasi.KEY_KEPERLUAN,keperluan);
                hashMap.put(Konfigurasi.KEY_TANGGAL,tanggal);
                hashMap.put(Konfigurasi.KEY_STATUS,status);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_UPDATE_TAMU,hashMap);

                return s;
            }
        }

        UpdateTamu updateTamu = new UpdateTamu();
        updateTamu.execute();
    }

    private void deleteTamu(){
        class DeleteTamu extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilTamuActivity.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilTamuActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_DELETE_TAMU, id);
                return s;
            }
        }

        DeleteTamu deleteTamu = new DeleteTamu();
        deleteTamu.execute();
    }

    private void confirmDeleteTamu(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda yakin akan menghapus data ini?");
        builder.setPositiveButton("Ya",
                (dialogInterface, i) -> {
                    deleteTamu();
                    startActivity(new Intent(TampilTamuActivity.this, LihatTamuActivity.class));
                    finish();
                });
        builder.setNegativeButton("Tidak", (dialogInterface, i) -> {});
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateTamu();
        }

        if(v == buttonDelete){
            confirmDeleteTamu();
        }
    }

    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), LihatTamuActivity.class);
        startActivity(i);
        finish();
    }
}