package com.example.lab2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContadorViewModel extends ViewModel {

    //From mi universo paralelo

    //Para q exista en el universo paralelo debe ser MutableLiveData
    private final MutableLiveData<Integer> contador = new MutableLiveData<>();

    public MutableLiveData<Integer> getContador(){
        return contador;
    }




}
