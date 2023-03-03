package com.yusuf.bukutamu;

public class Konfigurasi {
    //Dibawah ini adalah Pengalamatan tempat tersimpannya Lokasi Skrip PHP
    //Alamatnya tertuju ke file PHP dimana PHP file tersebut disimpan
    //JANGAN LUPA gunakan IP SESUAI DENGAN IP SERVER atau nama domain ya
    public static final String URL_SHOW_TAMU="http://192.168.122.41:8080/android_bukutamu/data/tamulihatdata.php";
    public static final String URL_ADD_TAMU="http://192.168.122.41:8080/android_bukutamu/data/tamutambah.php";

    public static final String URL_GET_TAMU ="http://192.168.122.41:8080/android_bukutamu/data/tampiltamu.php?id_tamu=";
    public static final String URL_UPDATE_TAMU ="http://192.168.122.41:8080/android_bukutamu/data/updatetamu.php";
    public static final String URL_DELETE_TAMU ="http://192.168.122.41:8080/android_bukutamu/data/hapustamu.php?id_tamu=";

    public static final String URL_LOGIN = "http://192.168.122.41:8080/android_bukutamu/data/LoginActivity.php";

    //Dibawah ini merupakan script atau perintah untuk mengirim permintaan ke dalam Skrip PHP
    public static final String KEY_IDTAMU       = "id_tamu";
    public static final String KEY_NAMA         = "nama_tamu";
    public static final String KEY_INSTANSI     = "instansi";
    public static final String KEY_ALAMAT       = "alamat";
    public static final String KEY_TELEPON      = "telepon";
    public static final String KEY_KEPERLUAN    = "keperluan";
    public static final String KEY_TANGGAL      = "tanggal";
    public static final String KEY_STATUS       = "status";
    public static final String KEY_USERNAME     = "username";
    public static final String KEY_PASSWORD     = "password";

    //JSON Tags
    public static final String TAG_JSON_ARRAY   = "result";
    public static final String TAG_IDTAMU       = "id_tamu";
    public static final String TAG_NAMA         = "nama_tamu";
    public static final String TAG_INSTANSI     = "instansi";
    public static final String TAG_ALAMAT       = "alamat";
    public static final String TAG_TELEPON      = "telepon";
    public static final String TAG_KEPERLUAN    = "keperluan";
    public static final String TAG_TANGGAL      = "tanggal";
    public static final String TAG_STATUS       = "status";

    //ID SISWA
    public static final String ID_TAMU = "id_tamu";
}