package com.example.uaustore.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.recyclerView.adapter.RVadapter_anunciados;
import com.example.uaustore.recyclerView.adapter.RVadapter_comprados;

public class Perfil_anunciados extends AppCompatActivity {

    RecyclerView recyclerAnunciados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_anunciados);

        System.out.println(ContaLogada.getContaLogada().getCompras_data());
        recyclerAnunciados = findViewById(R.id.recycler_anunciados);
        recyclerAnunciados.setAdapter(new RVadapter_anunciados(Item.getItens(this, ContaLogada.getContaLogada().getAnunciados()),this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerAnunciados.setLayoutManager(linearLayoutManager);
    }
}