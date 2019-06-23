package com.example.brasfutero.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.example.brasfutero.R;
import com.example.brasfutero.adapter.adapterEscalacao;
import com.example.brasfutero.model.Jogadores;
import com.example.brasfutero.model.RecyclerItemClickListener;
import com.example.brasfutero.model.Times;

import java.util.ArrayList;
import java.util.List;

public class escalacao extends AppCompatActivity {
    private RecyclerView listaJogadores;
    private List<Jogadores>  jogadores = new ArrayList<Jogadores>();
    private Bundle dados;
    private Cursor cursorTime, cursorJogadores;
    private SQLiteDatabase bd;
    private TextView nomeTime, nomeTecnico;
    private Intent intent;
    private ImageView escudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escalacao);

        bd = openOrCreateDatabase("banco8",MODE_PRIVATE,null);

        nomeTime = findViewById(R.id.tvNomeTime);
        nomeTecnico = findViewById(R.id.tvNomeTecnico);

        // Receber extras da intent
        int timeSelecionado;
        intent = getIntent();
        dados = intent.getExtras();
        timeSelecionado = dados.getInt("idTime");

        // Selecionar o time escolhido
        cursorTime = bd.rawQuery("SELECT * FROM times WHERE id='"+timeSelecionado+"'",null);
        cursorTime.moveToFirst();
        Times time = new Times();
        time.setId(cursorTime.getInt(cursorTime.getColumnIndex("id")));
        time.setNome(cursorTime.getString(cursorTime.getColumnIndex("nome")));
        time.setTecnico(cursorTime.getString(cursorTime.getColumnIndex("tecnico")));
        nomeTime.setText(""+time.getNome());
        nomeTecnico.setText(""+time.getTecnico());
        carregarEscudo();

        // Carregar recycler view jogadores do time
        listaJogadores = findViewById(R.id.rvListaJogadores);

        cursorJogadores = bd.rawQuery("SELECT * FROM jogadores WHERE id_time='"+timeSelecionado+"'",null);
        cursorJogadores.moveToFirst();
        if(cursorJogadores.moveToFirst()){
            do{
                Jogadores jogador = new Jogadores();
                jogador.setId(cursorJogadores.getInt(cursorJogadores.getColumnIndex("id")));
                jogador.setIdade(cursorJogadores.getInt(cursorJogadores.getColumnIndex("idade")));
                jogador.setNome(cursorJogadores.getString(cursorJogadores.getColumnIndex("nome")));
                jogador.setNacionalidade(cursorJogadores.getString(cursorJogadores.getColumnIndex("nacionalidade")));
                jogador.setPosicao(cursorJogadores.getString(cursorJogadores.getColumnIndex("posicao")));
                jogador.setGols(cursorJogadores.getInt(cursorJogadores.getColumnIndex("gols")));
                jogador.setAssistencia(cursorJogadores.getInt(cursorJogadores.getColumnIndex("assistencia")));
                jogador.setCA(cursorJogadores.getInt(cursorJogadores.getColumnIndex("CA")));
                jogador.setCV(cursorJogadores.getInt(cursorJogadores.getColumnIndex("CV")));
                jogadores.add(jogador);
            } while (cursorJogadores.moveToNext());
        }
        adapterEscalacao adapter = new adapterEscalacao(jogadores,dados.getInt("numeroRodadas"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaJogadores.setLayoutManager(layoutManager);
        listaJogadores.setHasFixedSize(true);
        listaJogadores.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        listaJogadores.setAdapter(adapter);

        // Adicionando eventos de clique a partir de classe já estabelecida
            listaJogadores.addOnItemTouchListener(
                    new RecyclerItemClickListener(
                            getApplicationContext(), listaJogadores, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            int idJogador = jogadores.get(position).getId();
                            editarJogador(view,idJogador);
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            Toast.makeText(getApplicationContext(), "Clique longo em " + jogadores.get(position).getNome(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    }
                    )
            );

    }

    public void editarJogador(View view, int idJogador){
        Intent novaIntent = new Intent(this, editar_jogador.class);
        novaIntent.putExtra("idJogador",idJogador);
        novaIntent.putExtra("idTime",dados.getInt("idTime"));
        startActivity(novaIntent);
    }

    public void carregarEscudo(){
        int id = cursorTime.getInt(cursorTime.getColumnIndex("id"));
        escudo = findViewById(R.id.ivTime);
        switch (id){
            case 1:
                escudo.setImageResource(R.drawable.santos);
                break;
            case 2:
                escudo.setImageResource(R.drawable.palmeiras);
                break;
            case 3:
                escudo.setImageResource(R.drawable.corinthians);
                break;
            case 4:
                escudo.setImageResource(R.drawable.saopaulo);
                break;
            case 5:
                escudo.setImageResource(R.drawable.flamengo);
                break;
            case 6:
                escudo.setImageResource(R.drawable.fluminense);
                break;
            case 7:
                escudo.setImageResource(R.drawable.vasco);
                break;
            case 8:
                escudo.setImageResource(R.drawable.botafogo);
                break;
            case 9:
                escudo.setImageResource(R.drawable.cruzeiro);
                break;
            case 10:
                escudo.setImageResource(R.drawable.atleticomg);
                break;
            case 11:
                escudo.setImageResource(R.drawable.internacional);
                break;
            case 12:
                escudo.setImageResource(R.drawable.gremio);
                break;
            case 13:
                escudo.setImageResource(R.drawable.bahia);
                break;
            case 14:
                escudo.setImageResource(R.drawable.ceara);
                break;
            case 15:
                escudo.setImageResource(R.drawable.fortaleza);
                break;
            case 16:
                escudo.setImageResource(R.drawable.athleticopr);
                break;
            case 17:
                escudo.setImageResource(R.drawable.goias);
                break;
            case 18:
                escudo.setImageResource(R.drawable.chapecoense);
                break;
            case 19:
                escudo.setImageResource(R.drawable.avai);
                break;
            case 20:
                escudo.setImageResource(R.drawable.csa);
                break;
        }
    }

}
