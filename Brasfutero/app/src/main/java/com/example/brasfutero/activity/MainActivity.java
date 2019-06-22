package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.brasfutero.R;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = openOrCreateDatabase("banco5",MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS times(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, tecnico VARCHAR)");
        bd.execSQL("CREATE TABLE IF NOT EXISTS jogadores(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, id_time INTEGER, idade INTEGER, posicao VARCHAR, nacionalidade VARCHAR, FOREIGN KEY (id_time) REFERENCES times(id))");
        bd.execSQL("CREATE TABLE IF NOT EXISTS saves(id INTEGER PRIMARY KEY AUTOINCREMENT, id_time INTEGER, FOREIGN KEY (id_time) REFERENCES times(id))");
        bd.execSQL("CREATE TABLE IF NOT EXISTS tabelas(id_save INTEGER, pontuacao INTEGER, id_time INTEGER, PRIMARY KEY (id_save, id_time), FOREIGN KEY (id_save) REFERENCES saves(id), FOREIGN KEY (id_time) REFERENCES times(id))");
        Cursor cursorTimes = bd.rawQuery("SELECT * FROM times",null);
        Cursor cursorJogadores = bd.rawQuery("SELECT * FROM jogadores",null);
        System.out.println("TESTE A");
        if(!cursorTimes.moveToFirst())
            criarTimes();
        if(!cursorJogadores.moveToFirst())
            criarJogadores();
    }

    public void novoJogo(View view){
        Intent intent = new Intent(this, inicio_jogo.class);
        startActivity(intent);
    }
/*
    public void editorJogo(View view){
        Intent intent = new Intent(this, editor_jogo.class);
        startActivity(intent);
    }*/

    public void criarTimes(){
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Santos','Sampaolli')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Palmeiras','Felipão')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Corinthians','Carille')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('São Paulo','Cuca')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Flamengo','Jorge Jesus')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Fluminense','Fernando Diniz')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Vasco','Luxemburgo')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Botafogo','Eduardo Barroca ')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Cruzeiro','Mano Menezes')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Atlético-MG','Rodrigo Santana')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Internacional','Odair Hellmann')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Grêmio','Renato Gaúcho')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Bahia','Roger Machado')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Ceará','Enderson Moreira')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Fortaleza','Rogério Ceni')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Athtletico-PR','Tiago Nunes')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Goiás','Claudinei Oliveira')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Chapecoense','Ney Franco')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('Avaí','Geninho')");
        bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('CSA','Marcelo Cabo')");
    }

    public void criarJogadores(){
        // Santos
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Everson','1','28','GOL','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Lucas Verissimo','1','23','ZAG','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Felipe Aguilar','1','26','ZAG','Colombiano')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Victor Ferraz','1','31','LAT','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Jorge','1','23','LAT','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Alison','1','26','MEI','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Diego Pituca','1','26','MEI','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Jean Motta','1','25','MEI','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Marinho','1','29','ATA','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Sasha','1','27','ATA','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Soteldo','1','21','ATA','Venezuelano')");

        // Palmeiras
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Weverton','2','20','GOL','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Antônio Carlos','2','20','ZAG','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Edu Dracena','2','20','ZAG','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Diego Barbosa','2','20','LAT','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Marcos Rocha','2','20','LAT','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Bruno Henrique','2','20','MEI','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Felipe Melo','2','20','MEI','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Lucas Lima','2','20','MEI','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Zé Rafael','2','20','ATA','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Dudu','2','20',''ATA','Brasileiro')");
        bd.execSQL("INSERT INTO Jogadores(nome,id_time,idade,posicao,nacionalidade) VALUES ('Deyverson','2','20','ATA','Brasileiro')");
    }

}
