package com.example.uaustore.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uaustore.R;
import com.example.uaustore.models.Conta;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logar = findViewById(R.id.logar);
        EditText usuarioemail = findViewById(R.id.usuarioemail);
        //System.out.println(ContaLogada.getContaLogada().getComprados());
        EditText senha = findViewById(R.id.senha);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




//                try {

                    Conta.logar(usuarioemail.getText().toString(), senha.getText().toString(), Login.this);
//                } catch (Exception e) {
//                    Toast.makeText(Login.this, "conta nao encontrada", Toast.LENGTH_SHORT).show();
//                }




            }
        });
    }
}