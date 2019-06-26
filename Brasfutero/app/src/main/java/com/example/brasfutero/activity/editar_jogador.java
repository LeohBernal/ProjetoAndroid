package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.brasfutero.R;
import com.example.brasfutero.model.Jogadores;

public class editar_jogador extends AppCompatActivity {
    private Intent intent;
    private Bundle dados;
    private EditText gols, assistencias, CAs, CVs;
    private Button atualizar;
    private SQLiteDatabase bd;
    private Cursor cursorJogador;
    private Jogadores jogador = new Jogadores();
    private TextView nomeJogador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_jogador);
        bd = openOrCreateDatabase("banco9",MODE_PRIVATE,null);

        intent = getIntent();
        dados = intent.getExtras();

        gols = findViewById(R.id.etGol);
        assistencias = findViewById(R.id.etAssist);
        CAs = findViewById(R.id.etCA);
        CVs = findViewById(R.id.etCV);
        atualizar = findViewById(R.id.btnAtualizarJogador);
        nomeJogador = findViewById(R.id.tvNomeJogadorAlterar);

        cursorJogador = bd.rawQuery("SELECT * FROM jogadores WHERE id='"+dados.getInt("idJogador")+"'",null);
        cursorJogador.moveToFirst();

        jogador.setNome(cursorJogador.getString(cursorJogador.getColumnIndex("nome")));
        jogador.setGols(cursorJogador.getInt(cursorJogador.getColumnIndex("gols")));
        jogador.setAssistencia(cursorJogador.getInt(cursorJogador.getColumnIndex("assistencia")));
        jogador.setCA(cursorJogador.getInt(cursorJogador.getColumnIndex("CA")));
        jogador.setCV(cursorJogador.getInt(cursorJogador.getColumnIndex("CV")));

        gols.setText(""+jogador.getGols());
        assistencias.setText(""+jogador.getAssistencia());
        CAs.setText(""+jogador.getCA());
        CVs.setText(""+jogador.getCV());
        nomeJogador.setText(""+jogador.getNome());

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gol, assistencia, CA, CV;
                gol = gols.getText().toString();
                assistencia = assistencias.getText().toString();
                CA = CAs.getText().toString();
                CV = CVs.getText().toString();
                if(!gol.isEmpty() && !assistencia.isEmpty() && !CA.isEmpty() && !CV.isEmpty()){
                bd.execSQL("UPDATE jogadores SET gols = '"+gol+"', assistencia = '"+assistencia+"', CA = '"+CA+"', CV = '"+CV+"' WHERE id = '"+dados.getInt("idJogador")+"'");
                retornarInicio(v);
            } else {
                    // FAZER ERROS
                }
            }
        });

    }

    public void retornarInicio(View view){
        Intent intent = new Intent(this, escalacao.class);
        intent.putExtra("sucesso",1);
        intent.putExtra("idTime",dados.getInt("idTime"));
        startActivity(intent);
    }

}
