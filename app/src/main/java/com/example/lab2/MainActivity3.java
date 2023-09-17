package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab2.databinding.ActivityMain3Binding;
import com.example.lab2.workers.ContadorWorker;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class MainActivity3 extends AppCompatActivity {

    private TextView contadorTextView;
    private Button iniciarRapidoBtn;

    private int contador = 227;
    private int tiempoDeEspera = 10000; // Inicialmente, 10 segundos

    private final Handler handler = new Handler();

    private boolean corriendo = false;

    private Vibrator vibrator;

    /*
    * Para el caso del contador se ha usado la Clase Runnable
    * La cual cual mediante un objeto Thread se ejecuta la funcion encomendada en un hilo
    * en background
    * La logica a seguir se define en el método llamado run()
    *
    * */
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (contador <= 226) {
                contadorTextView.setText(String.valueOf(contador));
                contador++;

                handler.postDelayed(this, tiempoDeEspera);
            } else {
                vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);

                // Cuando se alcanza 226, restablecer el botón a "Iniciar"
                iniciarRapidoBtn.setText("Iniciar");
                /*
                iniciarRapidoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startContador();
                    }
                });*/
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toast.makeText(MainActivity3.this,"Usted se encuentra en la vista del contador",Toast.LENGTH_SHORT).show();

        contadorTextView = findViewById(R.id.contadorTextView);
        iniciarRapidoBtn = findViewById(R.id.iniciarRapidoBtn);

        iniciarRapidoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(corriendo && contador <226 && tiempoDeEspera>1000){
                    tiempoDeEspera = tiempoDeEspera - 1000;
                    iniciarRapidoBtn.setText("Rápido (" + (tiempoDeEspera / 1000) + "s)");
                }

                if (contador > 226) {
                    startContador();
                    iniciarRapidoBtn.setText("Rápido (" + (tiempoDeEspera / 1000) + "s)");
                }

                if(contador==226){
                    corriendo = false;
                    tiempoDeEspera = 10000;
                    iniciarRapidoBtn.setText("Rápido (" + (tiempoDeEspera / 1000) + "s)");
                    contador++;
                }
            }
        });



    }
    private void startContador() {
        handler.removeCallbacks(runnable);
        contador = 104;
        contadorTextView.setText(String.valueOf(contador)); // Mostrar el valor inicial
        handler.postDelayed(runnable, tiempoDeEspera); // Comenzar con un retraso inicial de 10 segundos.
        corriendo = true;
    }
}