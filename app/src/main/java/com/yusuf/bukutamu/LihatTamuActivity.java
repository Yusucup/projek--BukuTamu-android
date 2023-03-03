package com.yusuf.bukutamu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LihatTamuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_tamu);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        getJSON();
    }

    private void showChachacha() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new
                ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result =
                    jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo       = result.getJSONObject(i);
                String id_tamu      = jo.getString(Konfigurasi.TAG_IDTAMU);
                String nama_tamu    = jo.getString(Konfigurasi.TAG_NAMA);
                String instansi     = jo.getString(Konfigurasi.TAG_INSTANSI);
                String alamat       = jo.getString(Konfigurasi.TAG_ALAMAT);
                String telepon      = jo.getString(Konfigurasi.TAG_TELEPON);
                String keperluan    = jo.getString(Konfigurasi.TAG_KEPERLUAN);
                String tanggal      = jo.getString(Konfigurasi.TAG_TANGGAL);
                String status       = jo.getString(Konfigurasi.TAG_STATUS);
                HashMap<String, String> lihatdatasuhu = new HashMap<>();
                lihatdatasuhu.put(Konfigurasi.TAG_IDTAMU, id_tamu);
                lihatdatasuhu.put(Konfigurasi.TAG_NAMA, nama_tamu);
                lihatdatasuhu.put(Konfigurasi.TAG_INSTANSI, instansi);
                lihatdatasuhu.put(Konfigurasi.TAG_ALAMAT, alamat);
                lihatdatasuhu.put(Konfigurasi.TAG_TELEPON, telepon);
                lihatdatasuhu.put(Konfigurasi.TAG_KEPERLUAN, keperluan);
                lihatdatasuhu.put(Konfigurasi.TAG_TANGGAL, tanggal);
                lihatdatasuhu.put(Konfigurasi.TAG_STATUS, status);
                list.add(lihatdatasuhu);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                LihatTamuActivity.this, list, R.layout.list_item,
                new String[]{
                        Konfigurasi.TAG_IDTAMU,
                        Konfigurasi.TAG_NAMA,
                        Konfigurasi.TAG_INSTANSI,
                        Konfigurasi.TAG_ALAMAT,
                        Konfigurasi.TAG_TELEPON,
                        Konfigurasi.TAG_KEPERLUAN,
                        Konfigurasi.TAG_TANGGAL,
                        Konfigurasi.TAG_STATUS
                },
                new int[]{
                        R.id.tv_item_id,
                        R.id.tv_item_nama,
                        R.id.tv_item_instansi,
                        R.id.tv_item_alamat,
                        R.id.tv_item_telepon,
                        R.id.tv_item_keperluan,
                        R.id.tv_item_tanggal,
                        R.id.tv_item_status
                });

        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatTamuActivity.this, "Mengambil Data", "Mohon Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showChachacha();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.URL_SHOW_TAMU);
                return s;
            }

        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilTamuActivity.class);
        HashMap <String, String> map = (HashMap) parent.getItemAtPosition(position);
        String id_tamu = map.get(Konfigurasi.TAG_IDTAMU).toString();
        intent.putExtra(Konfigurasi.KEY_IDTAMU, id_tamu);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}