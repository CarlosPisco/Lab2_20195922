package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private Chronometer chronometer;
    private Button chronometerBtn;
    private Button clearBtn;
    boolean isRunning = false;



    /*
    * Para el caso del cronometro se ha usado la clase Chronometer
    * Esta clase es usada para mostrar y gestionar el tiempo en la UI
    * Presenta la facilidad de mostrar el tiempo en formato de horas, minutos, segundos y miliseg
    * Además con la facilidad de tener metodos para iniciar, parar, reanudar y reiniciar el cronom
    * No se utliza para tareas de segundo plano como los hilos
    * Además su implementación es más sencilla al tener to.do incorporado
    * Con los hilos se me presento complicación al tener que pausar y despausar
    * Ya que la aplicación creabas más tareas en background que hacia lenta la interfaz
    * y finalmente se terminaba cerrando
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(MainActivity2.this,"Usted se encuentra en la vista del cronometro",Toast.LENGTH_SHORT).show();


        chronometer = findViewById(R.id.idCMmeter);
        chronometerBtn = findViewById(R.id.idBtnChronometer);
        clearBtn = findViewById(R.id.idBtnLimpiar);


        chronometerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle entre iniciar y detener el cronómetro
                if (isRunning) {
                    chronometerBtn.setText("Iniciar Cronómetro");
                    isRunning = false;
                    chronometer.stop();
                } else {
                    chronometerBtn.setText("Detener Cronómetro");
                    isRunning = true;
                    chronometer.start();
                }
            }
        });


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detener el cronómetro y restablece a cero
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometerBtn.setText("Iniciar Cronómetro");
                isRunning = false;
            }
        });


    }
}