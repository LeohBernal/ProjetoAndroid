package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brasfutero.R;
import com.example.brasfutero.model.Jogadores;

public class editar_jogador extends AppCompatActivity {
    private Intent intent;
    private Bundle dados;
    private SQLiteDatabase bd;
    private Cursor cursorJogador;
    private Jogadores jogador = new Jogadores();
    private ImageView plusGols, plusAssist, plusCA, plusCV, minusGols, minusAssist, minusCA, minusCV, save, cancel;
    private TextView gols, assist, CA, CV, nomeJogador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_jogador1);
        bd = openOrCreateDatabase("banco",MODE_PRIVATE,null);

        intent = getIntent();
        dados = intent.getExtras();

        // Setar os componentes
        plusGols = findViewById(R.id.ivPlusGols);
        plusAssist = findViewById(R.id.ivPlusAssist);
        plusCA = findViewById(R.id.ivPlusCA);
        plusCV  = findViewById(R.id.ivPlusCV);
        minusGols = findViewById(R.id.ivMinusGols);
        minusAssist = findViewById(R.id.ivMinusAssist);
        minusCA = findViewById(R.id.ivMInusCA);
        minusCV = findViewById(R.id.ivMinusCV);
        gols = findViewById(R.id.tvEdicNumGols);
        assist = findViewById(R.id.tvEdicNumAssist);
        CA = findViewById(R.id.tvEdicNumCA);
        CV = findViewById(R.id.tvEdicNumCV);
        nomeJogador = findViewById(R.id.tvEdicNomeJogador);
        save = findViewById(R.id.ivSalvarJogador);
        cancel = findViewById(R.id.ivCancelarJogador);


        // Carrega o jogador selecionado
        cursorJogador = bd.rawQuery("SELECT * FROM jogadores WHERE id='"+dados.getInt("idJogador")+"'",null);
        cursorJogador.moveToFirst();

        // Insere seus dados em um Jogadores
        jogador.setNome(cursorJogador.getString(cursorJogador.getColumnIndex("nome")));
        jogador.setGols(cursorJogador.getInt(cursorJogador.getColumnIndex("gols")));
        jogador.setAssistencia(cursorJogador.getInt(cursorJogador.getColumnIndex("assistencia")));
        jogador.setCA(cursorJogador.getInt(cursorJogador.getColumnIndex("CA")));
        jogador.setCV(cursorJogador.getInt(cursorJogador.getColumnIndex("CV")));

        // Apresenta os dados da tabela inicialmente
        gols.setText(""+jogador.getGols());
        assist.setText(""+jogador.getAssistencia());
        CA.setText(""+jogador.getCA());
        CV.setText(""+jogador.getCV());
        nomeJogador.setText(""+jogador.getNome());

        // Incremento ou decremento de gols, assist, CA, CV
        plusGols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarTV(1);
            }
        });
        plusAssist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarTV(2);
            }
        });
        plusCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarTV(3);
            }
        });
        plusCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarTV(4);
            }
        });
        minusGols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementarTV(1);
            }
        });
        minusAssist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementarTV(2);
            }
        });
        minusCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementarTV(3);
            }
        });
        minusCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementarTV(4);
            }
        });

        // Atualiza os dados do jogador
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gol, assistencia, CAs, CVs;
                gol = gols.getText().toString();
                assistencia = assist.getText().toString();
                CAs = CA.getText().toString();
                CVs = CV.getText().toString();
                bd.execSQL("UPDATE jogadores SET gols = '"+gol+"', assistencia = '"+assistencia+"', CA = '"+CAs+"', CV = '"+CVs+"' WHERE id = '"+dados.getInt("idJogador")+"'");
                retornarEscalacao(v,1);
            }
        });

        // Apenas volta a escalação
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornarEscalacao(v,0);
            }
        });

    }

    public void incrementarTV(int campo){
        int CAs, CVs;
        CAs = Integer.parseInt(CA.getText().toString());
        CVs = Integer.parseInt(CV.getText().toString());
            int valorCampo = 0;
            if(campo == 1) {
                valorCampo = Integer.parseInt(gols.getText().toString());
                valorCampo++;
                gols.setText(valorCampo+"");
            } else if(campo == 2){
                valorCampo = Integer.parseInt(assist.getText().toString());
                valorCampo++;
                assist.setText(valorCampo+"");
            } else if(campo == 3){
                if((CAs)+(CVs*2) < dados.getInt("numeroRodadas")) {
                    valorCampo = Integer.parseInt(CA.getText().toString());
                    valorCampo++;
                    CA.setText(valorCampo + "");
                }
            } else if(campo == 4){
                if(CAs+((CVs)*2) < dados.getInt("numeroRodadas")) {
                    valorCampo = Integer.parseInt(CV.getText().toString());
                    valorCampo++;
                    CV.setText(valorCampo + "");
                }
            }
    }

    public void decrementarTV(int campo){
        int gol, assists, CAs, CVs;
        gol = Integer.parseInt(gols.getText().toString());
        assists = Integer.parseInt(assist.getText().toString());
        CAs = Integer.parseInt(CA.getText().toString());
        CVs = Integer.parseInt(CV.getText().toString());
        int valorCampo = 0;
        if(campo == 1 && gol > 0) {
            valorCampo = Integer.parseInt(gols.getText().toString());
            valorCampo--;
            gols.setText(valorCampo+"");
        } else if(campo == 2 && assists > 0){
            valorCampo = Integer.parseInt(assist.getText().toString());
            valorCampo--;
            assist.setText(valorCampo+"");
        } else if(campo == 3 && CAs > 0){
            valorCampo = Integer.parseInt(CA.getText().toString());
            valorCampo--;
            CA.setText(valorCampo+"");
        } else if(campo == 4 && CVs > 0){
            valorCampo = Integer.parseInt(CV.getText().toString());
            valorCampo--;
            CV.setText(valorCampo+"");
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    // Retorna se houve ou não edição do jogador
    public void retornarEscalacao(View view, int check){
        Intent intent = new Intent(this,escalacao.class);
        intent.putExtra("numeroRodadas",dados.getInt("numeroRodadas"));
        intent.putExtra("idTime",dados.getInt("idTime"));
        if(check == 1)
            intent.putExtra("edicaoJogador",1);
        setResult(RESULT_OK, intent);
        finish();
    }

}
