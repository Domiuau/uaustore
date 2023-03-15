package com.example.uaustore.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uaustore.R;
import com.example.uaustore.models.Conta;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.ui.perfil.Perfil_usuario;

public class Entrada extends AppCompatActivity {

    Button inicio,cadastro,login,perfil,anunciarteste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        inicio = findViewById(R.id.inicio);
        cadastro = findViewById(R.id.cadastro);
        login = findViewById(R.id.login);
        perfil = findViewById(R.id.perfil_foto);
        anunciarteste = findViewById(R.id.anunciarteste);

        try {
            ContaLogada.criarContaLogada(Conta.getConta(Conta.getIdContaLogada(this),this));

        } catch (Exception e){


        }

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Entrada.this, Inicio.class));
            }
        });

        cadastro.setOnClickListener((View v) ->
                startActivity(new Intent(Entrada.this, Cadastro.class))

        );

        login.setOnClickListener((View v) -> startActivity(new Intent(Entrada.this, Login.class)));


        perfil.setOnClickListener((View view) -> {

                    startActivity(new Intent(Entrada.this, Perfil_usuario.class));
                    System.out.println(4545);

               });






        anunciarteste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Entrada.this, Item_anunciar.class));
            }
        });

    }
}