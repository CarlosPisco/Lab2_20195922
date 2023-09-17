package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab2.databinding.ActivityHomeBinding;
import com.example.lab2.databinding.ActivitySignUpBinding;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(HomeActivity.this,"Usted se encuentra en la vista home",Toast.LENGTH_SHORT).show();

        binding.username.setText(getIntent().getStringExtra("username"));
        String fullname = getIntent().getStringExtra("nombre") +" "+ getIntent().getStringExtra("apellido");
        binding.fullname.setText(fullname);

        ImageView imageView = findViewById(R.id.imageView);
        Picasso.get().load(getIntent().getStringExtra("thumbnail")).into(imageView);

        binding.button6.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this,MainActivity3.class);
            startActivity(intent);

        });


        binding.button8.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this,MainActivity2.class);
            startActivity(intent);

        });

    }
}