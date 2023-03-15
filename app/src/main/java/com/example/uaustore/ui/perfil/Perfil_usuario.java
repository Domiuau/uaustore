package com.example.uaustore.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uaustore.R;
import com.example.uaustore.models.Conta;
import com.example.uaustore.models.ContaLogada;


public class Perfil_usuario extends AppCompatActivity {

    ImageView perfil_foto;
    TextView vendasRealizadas, nome, email, data,saldo;
    ConstraintLayout sairdaconta,comprados,anunciados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        perfil_foto = findViewById(R.id.perfil_foto);
        email = findViewById(R.id.email);
        nome = findViewById(R.id.nome);
        data = findViewById(R.id.data);
        sairdaconta = findViewById(R.id.deslogar);
        saldo = findViewById(R.id.saldo);
        comprados = findViewById(R.id.comprados);
        anunciados = findViewById(R.id.anunciados);
        vendasRealizadas = findViewById(R.id.vendas_realizadas);
        perfil_foto.setClipToOutline(true);
        perfil_foto.setOutlineProvider(new ViewOutlineProvider() {
            @Override
           public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.teclado);
        Conta conta = ContaLogada.getContaLogada();
        vendasRealizadas.setText("Total de vendas realizadas: " + conta.getVendas());
        nome.setText("Nome de usuário: " + conta.getNome());
        email.setText("Email: " + conta.getEmail());
        perfil_foto.setImageBitmap(conta.getFoto_perfil());
        data.setText("Membro desde: " + conta.getDatacriacao());
        saldo.setText("Saldo atual: R$" + conta.getSaldo() +"");

        System.out.println(conta.getAnunciados());
      System.out.println(conta.getComprados().size());

      comprados.setOnClickListener((View view) -> {

          startActivity(new Intent(this,Perfil_comprados.class));
      });

      anunciados.setOnClickListener((View view) -> startActivity(new Intent(this, Perfil_anunciados.class)));

        sairdaconta.setOnClickListener((View view) -> {
//                SqliteHelper_conta sqliteHelper_conta = new SqliteHelper_conta(Perfil_usuario.this);
//                sqliteHelper_conta.deslogar();
//                startActivity(new Intent(Perfil_usuario.this, Login.class));

//            Conta conta1 = new Conta(Perfil_usuario.this);
//
//                    conta.setUsuario("carr8934");
//                    conta.updateSqlite("usuario","teste");
//                    System.out.println(conta.getUsuario() + " saida sqlite");




            }
        );












    }
}