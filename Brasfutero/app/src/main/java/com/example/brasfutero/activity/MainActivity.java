package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brasfutero.R;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bd;
    private Button rodada;
    private EditText numRodadas;
    private Cursor cursorRodadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = openOrCreateDatabase("banco9",MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS times(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, tecnico VARCHAR, vitoria INTEGER, derrota INTEGER, empate INTEGER)");
        bd.execSQL("CREATE TABLE IF NOT EXISTS jogadores(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, id_time INTEGER, idade INTEGER, posicao VARCHAR, nacionalidade VARCHAR, gols INTEGER, assistencia INTEGER, CA INTEGER, CV INTEGER, FOREIGN KEY (id_time) REFERENCES times(id))");
        bd.execSQL("CREATE TABLE IF NOT EXISTS rodadas(id INTEGER PRIMARY KEY AUTOINCREMENT, rodada INTEGER)");
        cursorRodadas = bd.rawQuery("SELECT * FROM rodadas",null);
        Cursor cursorTimes = bd.rawQuery("SELECT * FROM times",null);
        Cursor cursorJogadores = bd.rawQuery("SELECT * FROM jogadores",null);

        if(!cursorTimes.moveToFirst())
            criarTimes();
        if(!cursorJogadores.moveToFirst())
            criarJogadores();
        if(!cursorRodadas.moveToFirst())
            bd.execSQL("INSERT INTO rodadas(rodada) VALUES ('0')");

        rodada = findViewById(R.id.btnAtualizarNumRodadas);
        numRodadas = findViewById(R.id.etNumRodadas);

        numRodadas.setText(""+cursorRodadas.getInt(cursorRodadas.getColumnIndex("rodada")));

        rodada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numeroRodadasInteiro;
                String numeroRodadas;
                numeroRodadas = numRodadas.getText().toString();
                numeroRodadasInteiro = Integer.parseInt(numeroRodadas);
                if(numeroRodadasInteiro > 0 && numeroRodadasInteiro < 38){
                    bd.execSQL("UPDATE rodadas SET rodada = '"+numeroRodadas+"'");
                    Toast.makeText(getApplicationContext(),"Número de rodadas atualizado!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Número de rodadas é invalido!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void novoJogo(View view){
        Intent intent = new Intent(this, inicio_jogo.class);
        intent.putExtra("numeroRodadas",cursorRodadas.getInt(cursorRodadas.getColumnIndex("rodada")));
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
    
    public void criarTimes(){
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Santos','Sampaolli','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Palmeiras','Felipão','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Corinthians','Carille','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('São Paulo','Cuca','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Flamengo','Jorge Jesus','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Fluminense','Fernando Diniz','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Vasco','Luxemburgo','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Botafogo','Eduardo Barroca ','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Cruzeiro','Mano Menezes','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Atlético-MG','Rodrigo Santana','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Internacional','Odair Hellmann','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Grêmio','Renato Gaúcho','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Bahia','Roger Machado','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Ceará','Enderson Moreira','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Fortaleza','Rogério Ceni','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Athtletico-PR','Tiago Nunes','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Goiás','Claudinei Oliveira','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Chapecoense','Ney Franco','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('Avaí','Geninho','0','0','0')");
        bd.execSQL("INSERT INTO times(nome,tecnico,vitoria,derrota,empate) VALUES ('CSA','Marcelo Cabo','0','0','0')");
    }

    public void criarJogadores(){
        // Santos
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Everson','1','28','GOL','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Lucas Verissimo','1','23','ZAG','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Felipe Aguilar','1','26','ZAG','Colombiano','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Victor Ferraz','1','31','LAT','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Jorge','1','23','LAT','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Alison','1','26','MEI','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Diego Pituca','1','26','MEI','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Jean Motta','1','25','MEI','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Marinho','1','29','ATA','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Sasha','1','27','ATA','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Soteldo','1','21','ATA','Venezuelano','0','0','0','0')");

        // Palmeiras
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Weverton','2','20','GOL','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Antônio Carlos','2','20','ZAG','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Edu Dracena','2','20','ZAG','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Diego Barbosa','2','20','LAT','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Marcos Rocha','2','20','LAT','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Bruno Henrique','2','20','MEI','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Felipe Melo','2','20','MEI','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Lucas Lima','2','20','MEI','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Zé Rafael','2','20','ATA','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Dudu','2','20','ATA','Brasileiro','0','0','0','0')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Deyverson','2','20','ATA','Brasileiro','0','0','0','0')");
    }

}
