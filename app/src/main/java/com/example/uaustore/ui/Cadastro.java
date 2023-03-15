package com.example.uaustore.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.uaustore.R;
import com.example.uaustore.models.Conta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Cadastro extends AppCompatActivity {

    EditText input_usuario, input_email, input_senha, input_nome;
    ToggleButton input_privada;
    ImageView input_foto;
    Bitmap image;
    Button criar_conta;
    private static final int PICK_IMAGE = 100;
    private static final int RESULT_OK = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        input_usuario = findViewById(R.id.input_usuario);
        input_email = findViewById(R.id.input_email);
        input_senha = findViewById(R.id.input_senha);
        input_nome = findViewById(R.id.input_nome);
        input_privada = findViewById(R.id.input_privada);
        input_foto = findViewById(R.id.input_foto);
        criar_conta = findViewById(R.id.criar);

//                SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("DB_contas",MODE_PRIVATE,null);
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS contas(usuario VARCHAR, senha VARCHAR, nome VARCHAR, comprados VARCHAR," +
//                "anunciados VARCHAR, adm BOOLEAN, id INT(2), imagem_perfil BLOB)");

        criar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (image != null){





                    try {
                        Conta.atualizarID();

                    } catch (Exception e){
                        Conta.setContadorID(Conta.getContadorID() + 1);
                    }


                    new Conta(input_usuario.getText().toString(),input_email.getText().toString(), input_senha.getText().toString(), input_nome.getText().toString(),
                            input_privada.isChecked(),image,Cadastro.this);





                } else {
                    Toast.makeText(Cadastro.this,"adicione uma imagem",Toast.LENGTH_SHORT).show();
                }





//                Conta conta = new Conta(input_usuario.getText().toString(),
//                        input_senha.getText().toString(), input_nome.getText().toString(),
//                        input_email.getText().toString(), input_privada.isChecked(), image);
//
//                ContentValues values = new ContentValues();
//                values.put("usuario", "johndoe");
//                values.put("senha", "password123");
//                values.put("nome", "John Doe");
//                values.put("comprados", 0);
//                values.put("anunciados", 0);
//                values.put("adm", 0);
//                values.put("id", 1);
//
//                sqLiteDatabase.insert("contas", null, values);
//
////                sqLiteDatabase.execSQL("INSERT INTO contas (usuario, senha, nome, comprados, anunciados, adm, id) VALUES ('" +  conta.getUsuario() + "' " +
////                        "+ '" + conta.getSenha() + "' + '" + conta.getNome() + "' + ' null ' + 'null' + 0 +  21 ");
//
//                SqliteHelper_conta sqliteHelper = new SqliteHelper_conta(Cadastro.this);
//                sqliteHelper.insertImage(image, conta.getId());

            }
        });

        input_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });






    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap arrumarfoto = Bitmap.createScaledBitmap(bitmap,556,556, true);

                image = arrumarfoto;
                input_foto.setImageBitmap(arrumarfoto);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}