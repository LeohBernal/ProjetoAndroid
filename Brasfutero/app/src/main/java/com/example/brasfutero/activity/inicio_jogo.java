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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.brasfutero.adapter.adapterTimes;
import com.example.brasfutero.model.RecyclerItemClickListener;
import com.example.brasfutero.model.Times;
import com.example.brasfutero.R;
import java.util.ArrayList;
import java.util.List;

public class inicio_jogo extends AppCompatActivity {
    private SQLiteDatabase bd;
    private Cursor cursorTimes;
    private Intent intent;
    private Bundle dados;
    private RecyclerView listaTimes;
    private List<Times> times = new ArrayList<Times>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_jogo);
        bd = openOrCreateDatabase("banco10",MODE_PRIVATE,null);

        intent = getIntent();
        dados = intent.getExtras();

        if(intent.hasExtra("edicaoTime")){
            if(dados.getInt("edicaoTime") == 1)
                Toast.makeText(getApplicationContext(),"Time editado com sucesso!",Toast.LENGTH_SHORT).show();
        }


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

        System.out.println("TESTE NUM RODADAS:" +dados.getInt("numeroRodadas"));
        
        adapterTimes adapter = new adapterTimes(times, dados.getInt("numeroRodadas"), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaTimes.setLayoutManager(layoutManager);
        listaTimes.setHasFixedSize(true);
        listaTimes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        listaTimes.setAdapter(adapter);

    }

}
