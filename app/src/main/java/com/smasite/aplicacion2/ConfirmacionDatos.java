package com.smasite.aplicacion2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmacionDatos extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvNacimiento;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    private TextView tvNombrer;
    private TextView tvNacimientor;
    private TextView tvTelefonor;
    private TextView tvEmailr;
    private TextView tvDescripcionr;
    protected static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos);

        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString(getResources().getString(R.string.pnombre));
        String nacimiento = parametros.getString(getResources().getString(R.string.pnacimiento));
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        String email = parametros.getString(getResources().getString(R.string.pemail));
        String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion));


        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvNacimiento = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);


        tvNombre.setText(nombre);
        tvNacimiento.setText(nacimiento);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);

    }

    public void editarDatos(View view) {
        tvNombrer = (TextView) findViewById(R.id.tvNombre);
        tvNacimientor = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvTelefonor = (TextView) findViewById(R.id.tvTelefono);
        tvEmailr = (TextView) findViewById(R.id.tvEmail);
        tvDescripcionr = (TextView) findViewById(R.id.tvDescripcion);
        Intent intent = new Intent(ConfirmacionDatos.this, MainActivity.class);
        intent.putExtra(getResources().getString(R.string.pnombre), tvNombrer.getText().toString());
        intent.putExtra(getResources().getString(R.string.pnacimiento), tvNacimientor.getText().toString());
        intent.putExtra(getResources().getString(R.string.ptelefono), tvTelefonor.getText().toString());
        intent.putExtra(getResources().getString(R.string.pemail), tvEmailr.getText().toString());
        intent.putExtra(getResources().getString(R.string.pdescripcion), tvDescripcionr.getText().toString());
        startActivity(intent);
        finish();
    }

    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }

    public void enviarEmail(View v){
        String email = tvEmail.getText().toString();
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
