package com.example.uaustore.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.recyclerView.adapter.RVadapter_comprados;

public class Perfil_comprados extends AppCompatActivity {

    RecyclerView recyclerComprados;
    RVadapter_comprados rVadapter_comprados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_comprados);


    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerComprados = findViewById(R.id.recycler_comprados);
        rVadapter_comprados = new RVadapter_comprados(Item.getItensMatrix(this, ContaLogada.getContaLogada().getComprados()),this);
        recyclerComprados.setAdapter(rVadapter_comprados);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerComprados.setLayoutManager(linearLayoutManager);



    }
}