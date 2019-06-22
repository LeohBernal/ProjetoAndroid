package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.brasfutero.model.Times;

import com.example.brasfutero.R;

import java.util.ArrayList;
import java.util.List;

public class inicio_jogo extends AppCompatActivity {
    private Spinner spinner;
    private Boolean timeSelecionado, tecnicoDigitado;
    private Button iniciarJogo;
    private Times selecionado;
    private SQLiteDatabase bd;
    private Cursor cursorTimes, cursorSaves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_jogo);
        bd = openOrCreateDatabase("banco5",MODE_PRIVATE,null);
        spinner = findViewById(R.id.spinner);
        iniciarJogo = findViewById(R.id.btnIniciarJogo);

        cursorTimes = bd.rawQuery("SELECT * FROM times",null);

        List<Times> times = new ArrayList<Times>();

        System.out.println("TESTE 1");

        if(cursorTimes.moveToFirst()) {
            do {

                System.out.println("TESTE 2");
                Times time = new Times();
                time.setId(cursorTimes.getInt(cursorTimes.getColumnIndex("id")));
                time.setNome(cursorTimes.getString(cursorTimes.getColumnIndex("nome")));
                time.setTecnico(cursorTimes.getString(cursorTimes.getColumnIndex("tecnico")));
                times.add(time);

            } while (cursorTimes.moveToNext());
        }
        System.out.println("TESTE 3");

        ArrayAdapter<Times> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, times);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selecionado = (Times) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing
            }
        });

        iniciarJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia um novo jogando criando um save e gerando a tabela do save
                    bd.execSQL("INSERT INTO saves(id_time) VALUES ('"+selecionado.getId()+"')");
                    cursorSaves = bd.rawQuery("SELECT * FROM saves",null);
                    gerarTabela();
                    iniciarJogo(v);
            }
        });

    }

    public void iniciarJogo(View view){
        Intent intent = new Intent(this, escalacao.class);
        intent.putExtra("idTime",selecionado.getId());
        startActivity(intent);
    }

    public void gerarTabela(){
        // Volta o cursor dos times para o inicio para inserir na tabela
        // Pega o último save (o criado ao iniciar no jogo) e insere na tabela
        // Insere todos times na tabela com 0 pontos
        if(cursorTimes.moveToFirst()) {
            int id, id_save;
            cursorSaves.moveToLast();
            id_save = cursorSaves.getInt(cursorTimes.getColumnIndex("id"));
            do {
                id = cursorTimes.getInt(cursorTimes.getColumnIndex("id"));
                System.out.println("VALOR SAVE: "+id_save+"|| VALOR ID: "+id);
                bd.execSQL("INSERT INTO tabelas(id_save, id_time, pontuacao) VALUES ('"+id_save+"','"+id+"','0')");
            } while (cursorTimes.moveToNext());
        }
    }

}
