package com.example.listadetarefasapp;


import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.listadetarefasapp.R;
import com.example.listadetarefasapp.TelaAddTarefaActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends TelaAddTarefaActivity {

    FloatingActionButton butao_add;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        butao_add = findViewById(R.id.butao_add_tarefa);
        butao_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adicionarTarefa = new Intent(MainActivity.this, TelaAddTarefaActivity.class);
                startActivity(adicionarTarefa);
            }


        });


    }
}