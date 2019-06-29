package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brasfutero.R;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bd;
    private Button rodada;
    private TextView numRodadas;
    private Cursor cursorRodadas;
    private ImageView save, plus, minus, duvida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Tratamento do banco
        bd = openOrCreateDatabase("banco",MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS times(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, tecnico VARCHAR, vitoria INTEGER, derrota INTEGER, empate INTEGER)");
        bd.execSQL("CREATE TABLE IF NOT EXISTS jogadores(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, id_time INTEGER, idade INTEGER, posicao VARCHAR, nacionalidade VARCHAR, gols INTEGER, assistencia INTEGER, CA INTEGER, CV INTEGER, FOREIGN KEY (id_time) REFERENCES times(id))");
        bd.execSQL("CREATE TABLE IF NOT EXISTS rodadas(id INTEGER PRIMARY KEY AUTOINCREMENT, rodada INTEGER)");



        // Verifica se já existem os times, jogadores e o numero de rodadas, caso contrário insere
        Cursor cursorTimes = bd.rawQuery("SELECT * FROM times",null);
        Cursor cursorJogadores = bd.rawQuery("SELECT * FROM jogadores",null);
        cursorRodadas = bd.rawQuery("SELECT * FROM rodadas",null);
        if(!cursorTimes.moveToFirst())
            criarTimes();
        if(!cursorJogadores.moveToFirst())
            criarJogadores();
        if(!cursorRodadas.moveToFirst())
            bd.execSQL("INSERT INTO rodadas(rodada) VALUES ('0')");


        // Relaciona os componentes
        save = findViewById(R.id.ivSaveNumRod);
        plus = findViewById(R.id.ivPlusRodada);
        minus = findViewById(R.id.ivMinusRodada);
        numRodadas = findViewById(R.id.tvNumRodadaMain);
        duvida = findViewById(R.id.ivDuvida);

        // Insere o número de rodadas presente no banco
        cursorRodadas = bd.rawQuery("SELECT * FROM rodadas",null);
        cursorRodadas.moveToFirst();
        numRodadas.setText(""+cursorRodadas.getInt(cursorRodadas.getColumnIndex("rodada")));


        // Incrementa ou decrementa o número de rodadas
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numRodadas(1);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numRodadas(2);
            }
        });

        // Atualiza o número de rodadas no banco
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numeroRodadasInteiro = Integer.parseInt(numRodadas.getText().toString());
                bd.execSQL("UPDATE rodadas SET rodada = '"+numeroRodadasInteiro+"'");
                Toast.makeText(getApplicationContext(),"Número de rodadas atualizado!",Toast.LENGTH_SHORT).show();
            }
        });

        // Mostra toast para esclarecer dúvidas
        duvida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Selecione a imagem do disquete para atualizar o número de rodadas",Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Ao clicar no botão estatísca inicia a tela de estatiscas dos times, passando como parametro o número de rodadas
    public void novoJogo(View view){
        Intent intent = new Intent(this, estatiscas_times.class);
        cursorRodadas = bd.rawQuery("SELECT * FROM rodadas",null);
        cursorRodadas.moveToFirst();
        intent.putExtra("numeroRodadas",cursorRodadas.getInt(cursorRodadas.getColumnIndex("rodada")));
        startActivity(intent);
    }

    public void numRodadas(int check){
        int numeroRodadasInteiro;
        numeroRodadasInteiro = Integer.parseInt(numRodadas.getText().toString());
        if(check == 1){
            if(numeroRodadasInteiro < 38) {
                numeroRodadasInteiro++;
                numRodadas.setText(numeroRodadasInteiro+"");
            }
        } else if (check == 2){
            if(numeroRodadasInteiro > 0) {
                numeroRodadasInteiro--;
                numRodadas.setText(numeroRodadasInteiro+"");
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
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

        // Resto
        for(int i=2;i<=20;i++){
            for (int j=0;j<11;j++){
                bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade,gols,assistencia,CA,CV) VALUES ('Jogador "+(j+1)+"','"+i+"','20','GOL','Brasileiro','0','0','0','0')");
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        cursorRodadas = bd.rawQuery("SELECT * FROM rodadas",null);
        cursorRodadas.moveToFirst();
        numRodadas.setText(""+cursorRodadas.getInt(cursorRodadas.getColumnIndex("rodada")));
    }

}