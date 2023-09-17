package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lab2.databinding.ActivityHomeBinding;
import com.example.lab2.databinding.ActivityMain3Binding;
import com.example.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button9.setOnClickListener(v->{

            if(tieneInternet()){
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Debe tener conexion a internet",Toast.LENGTH_SHORT).show();
            }



        });

        Toast.makeText(this,"Tiene Internet: "+tieneInternet(),Toast.LENGTH_SHORT).show();




    }


    public boolean tieneInternet(){
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //Este me da la informacion
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test","Internet: " + tieneInternet);
        return  tieneInternet;
    }

}