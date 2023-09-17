package com.example.lab2;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//Para que cuando inicie la app tenga hilos listos
public class ApplicationThreads extends Application {

    public ExecutorService executorService = Executors.newFixedThreadPool(4);

}
