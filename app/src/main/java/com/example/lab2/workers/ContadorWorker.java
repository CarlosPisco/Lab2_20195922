package com.example.lab2.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

//incluso cuando se cierra la app
public class ContadorWorker extends Worker {

    public ContadorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    //no puede acceder al ViewModel
    //no puede hacer esto: contadorViewModel.getContador().postValue(i);
    public Result doWork() {
        int contador = 0;

        for (int i = 1; i <= 20; i++) {

            Log.d("msg-test", "i: " + i);

            setProgressAsync(new Data.Builder().putInt("contador", contador).build());
            contador++;


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return Result.failure();
            }
        }

        return Result.success();
    }

}
