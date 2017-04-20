package com.smasite.aplicacion2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static TextView SelectedDateView;
    private TextView tvNombre;
    private TextView tvNacimiento;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    private EditText tvNombrer;
    private TextView tvNacimientor;
    private EditText tvTelefonor;
    private EditText tvEmailr;
    private EditText tvDescripcionr;

    protected static final int REQUEST_CODE = 10;
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = 1990;
            int month = 00;
            int day = 01;

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth, this, year, month, day);
            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            SelectedDateView.setText( day + " / " + (month + 1) + " / " + year);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SelectedDateView = (TextView) findViewById(R.id.tvFechaNacimiento);

        Bundle parametros = getIntent().getExtras();
        if(parametros != null) {
            regreso();
        }
    }

    public void regreso(){
        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString(getResources().getString(R.string.pnombre));
        String nacimiento = parametros.getString(getResources().getString(R.string.pnacimiento));
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        String email = parametros.getString(getResources().getString(R.string.pemail));
        String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion));

        tvNombrer = (EditText) findViewById(R.id.etNombre);
        tvNacimientor = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvTelefonor = (EditText) findViewById(R.id.etTelefono);
        tvEmailr = (EditText) findViewById(R.id.etEmail);
        tvDescripcionr = (EditText) findViewById(R.id.etDescripcion);



            tvNombrer.setText(nombre);
            tvNacimientor.setText(nacimiento);
            tvTelefonor.setText(telefono);
            tvEmailr.setText(email);
            tvDescripcionr.setText(descripcion);

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void confirmar(View view) {
        tvNombre = (TextView) findViewById(R.id.etNombre);
        tvNacimiento = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvTelefono = (TextView) findViewById(R.id.etTelefono);
        tvEmail = (TextView) findViewById(R.id.etEmail);
        tvDescripcion = (TextView) findViewById(R.id.etDescripcion);

        Intent intent = new Intent(MainActivity.this, ConfirmacionDatos.class);
        intent.putExtra(getResources().getString(R.string.pnombre), tvNombre.getText().toString());
        intent.putExtra(getResources().getString(R.string.pnacimiento), tvNacimiento.getText().toString());
        intent.putExtra(getResources().getString(R.string.ptelefono), tvTelefono.getText().toString());
        intent.putExtra(getResources().getString(R.string.pemail), tvEmail.getText().toString());
        intent.putExtra(getResources().getString(R.string.pdescripcion), tvDescripcion.getText().toString());
        startActivity(intent);
        finish();
    }



}
