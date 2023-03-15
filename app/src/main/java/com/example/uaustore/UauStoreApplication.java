package com.example.uaustore;

import android.app.Application;
import android.content.Intent;

import com.example.uaustore.models.Conta;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.ui.Login;

public class UauStoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //this.startActivity(new Intent(this, Login.class));

//
//        try {
//                ContaLogada.criarContaLogada(Conta.getConta(Conta.getIdContaLogada(this),this));
//
//        } catch (Exception e){
//
//            this.startActivity(new Intent(this, Login.class));
//
//        }


    }
}
