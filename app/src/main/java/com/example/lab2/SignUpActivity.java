package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lab2.databinding.ActivitySignUpBinding;
import com.example.lab2.dto.Perfil;
import com.example.lab2.dto.Result;
import com.example.lab2.services.ProfileService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {


    private String thumbnail;

    private ActivitySignUpBinding binding;
    private String username;
    private String nombre;
    private String apellido;


    ProfileService profileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Toast.makeText(SignUpActivity.this,"Usted se encuentra en la vista de registro",Toast.LENGTH_SHORT).show();

        profileService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProfileService.class);

       fetchWebServiceData();

        binding.create.setOnClickListener(v->{

            boolean isChecked = binding.check.isChecked();

            if(isChecked){
                Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);

                intent.putExtra("nombre",nombre) ;
                intent.putExtra("apellido",apellido);

                intent.putExtra("thumbnail",thumbnail);
                intent.putExtra("username",username);
                startActivity(intent);
            }else{
                Toast.makeText(SignUpActivity.this,"Debes aceptar los Términos de Servicio y la Política de Privacidad",Toast.LENGTH_SHORT).show();
            }


        });

    }


    public void fetchWebServiceData(){

        if(tieneInternet()){
            //se ejecuta el webservice
            //background
            profileService.getPerfil().enqueue(new Callback<Perfil>() {
                //esto ya es asincrono
                @Override
                public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                    //pero aca dentro es sincrono, la rpta es devulta al ui preincipal
                    //osea puedo mostar el resultado en la interfaz
                    if(response.isSuccessful()){
                        Perfil perfil = response.body();
                        List<Result> resultados = perfil.getResults();


                         binding.nombre.setText(resultados.get(0).getName().getFirst());
                         binding.nombre.setFocusable(false);
                         binding.nombre.setFocusableInTouchMode(false);
                         binding.apellido.setText(resultados.get(0).getName().getLast());
                        binding.apellido.setFocusable(false);
                        binding.apellido.setFocusableInTouchMode(false);
                         binding.correo.setText(resultados.get(0).getEmail());
                        binding.correo.setFocusable(false);
                        binding.correo.setFocusableInTouchMode(false);
                         binding.password.setText(resultados.get(0).getLogin().getPassword());
                        binding.password.setFocusable(false);
                        binding.password.setFocusableInTouchMode(false);

                        thumbnail = resultados.get(0).getPicture().getLarge();
                        username = resultados.get(0).getLogin().getUsername();
                        nombre = resultados.get(0).getName().getFirst();
                        apellido = resultados.get(0).getName().getLast();
                    }
                }

                @Override
                public void onFailure(Call<Perfil> call, Throwable t) {

                }
            });
        }

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