package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.brasfutero.adapter.adapterTimes;
import com.example.brasfutero.model.Times;
import com.example.brasfutero.R;
import java.util.ArrayList;
import java.util.List;

public class estatiscas_times extends AppCompatActivity {
    private SQLiteDatabase bd;
    private Cursor cursorTimes;
    private Intent intent;
    private Bundle dados;
    private RecyclerView listaTimes;
    private List<Times> times = new ArrayList<Times>();
    private adapterTimes adapter;
    private ImageView duvida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estatiscas_times);
        bd = openOrCreateDatabase("banco",MODE_PRIVATE,null);

        intent = getIntent();
        dados = intent.getExtras();

        duvida = findViewById(R.id.ivDuvida1);

        // Carrega todos os times do banco em uma list
        cursorTimes = bd.rawQuery("SELECT * FROM times",null);
        if(cursorTimes.moveToFirst()) {
            do {
                Times time = new Times();
                time.setId(cursorTimes.getInt(cursorTimes.getColumnIndex("id")));
                time.setNome(cursorTimes.getString(cursorTimes.getColumnIndex("nome")));
                time.setTecnico(cursorTimes.getString(cursorTimes.getColumnIndex("tecnico")));
                time.setVitoria(cursorTimes.getInt(cursorTimes.getColumnIndex("vitoria")));
                time.setDerrota(cursorTimes.getInt(cursorTimes.getColumnIndex("derrota")));
                time.setEmpate(cursorTimes.getInt(cursorTimes.getColumnIndex("empate")));
                times.add(time);
            } while (cursorTimes.moveToNext());
        }
        
        // Carregar recycler view jogadores do time
        listaTimes = findViewById(R.id.rvTimes);

        // Gera o adapter para a lista de times, passando como parâmetro o número de rodadas para cálculo das estatíscas das equipes
        adapter = new adapterTimes(times, dados.getInt("numeroRodadas"), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaTimes.setLayoutManager(layoutManager);
        listaTimes.setHasFixedSize(true);
        listaTimes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        listaTimes.setAdapter(adapter);

        duvida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Selecione a equipe para verificar as estatísticas do jogadores\nSelecione a imagem de edição da equipe para edita-la",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    // Recebe os parâmetros quando é finalizada a activity de edição de equipe, para mostrar um Toast com sucesso de edição caso tenha ocorrido.
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                if (data.getIntExtra("edicaoTime", 0) == 1) {
                    Toast.makeText(getApplicationContext(), "Time editado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // Ao retornar a tela, limpa a lista de equipes e insere novamente para apresentar todos os dados atualizados
    @Override
    protected void onResume() {
        super.onResume();
        times.clear();
        cursorTimes = bd.rawQuery("SELECT * FROM times",null);
        if(cursorTimes.moveToFirst()) {
            do {
                Times time = new Times();
                time.setId(cursorTimes.getInt(cursorTimes.getColumnIndex("id")));
                time.setNome(cursorTimes.getString(cursorTimes.getColumnIndex("nome")));
                time.setTecnico(cursorTimes.getString(cursorTimes.getColumnIndex("tecnico")));
                time.setVitoria(cursorTimes.getInt(cursorTimes.getColumnIndex("vitoria")));
                time.setDerrota(cursorTimes.getInt(cursorTimes.getColumnIndex("derrota")));
                time.setEmpate(cursorTimes.getInt(cursorTimes.getColumnIndex("empate")));
                times.add(time);
            } while (cursorTimes.moveToNext());
        }
        adapter.notifyDataSetChanged();
    }



}
