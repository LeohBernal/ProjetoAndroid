package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brasfutero.R;
import com.example.brasfutero.model.Times;

public class editar_time extends AppCompatActivity {
    private Intent intent;
    private Bundle dados;
    private int numeroRodadas;
    private SQLiteDatabase bd;
    private Cursor cursorTime;
    private ImageView escudo, plusVit, plusDerrota, plusEmpate, minusVit, minusDerrota, minusEmpate, save, cancel;
    private TextView vitoria, empate, derrota;
    private int edicaoTime, timeSelecionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_time);
        intent = getIntent();
        dados = intent.getExtras();
        bd = openOrCreateDatabase("banco",MODE_PRIVATE,null);

        // Receber extras da intent
        intent = getIntent();
        dados = intent.getExtras();
        timeSelecionado = dados.getInt("idTime");
        numeroRodadas = dados.getInt("numeroRodadas");

        edicaoTime = 0;

        // Relaciona os componentes
        plusVit = findViewById(R.id.ivPlusVit);
        plusDerrota = findViewById(R.id.ivPlusDerrota);
        plusEmpate = findViewById(R.id.ivPlusEmpate);
        minusVit = findViewById(R.id.ivMinusVit);
        minusDerrota = findViewById(R.id.ivMInusDerrota);
        minusEmpate = findViewById(R.id.ivMinusEmpate);
        vitoria = findViewById(R.id.tvEdicNumAssist);
        derrota = findViewById(R.id.tvEdicNumCA);
        empate = findViewById(R.id.tvEdicNumCV);
        save = findViewById(R.id.ivSalvarTIme);
        cancel = findViewById(R.id.ivCancelarJogador);

        // Selecionar o time escolhido
        cursorTime = bd.rawQuery("SELECT * FROM times WHERE id='"+timeSelecionado+"'",null);
        cursorTime.moveToFirst();
        Times time = new Times();
        time.setId(cursorTime.getInt(cursorTime.getColumnIndex("id")));
        time.setNome(cursorTime.getString(cursorTime.getColumnIndex("nome")));
        time.setTecnico(cursorTime.getString(cursorTime.getColumnIndex("tecnico")));
        time.setVitoria(cursorTime.getInt(cursorTime.getColumnIndex("vitoria")));
        time.setDerrota(cursorTime.getInt(cursorTime.getColumnIndex("derrota")));
        time.setEmpate(cursorTime.getInt(cursorTime.getColumnIndex("empate")));
        carregarEscudo();

        // Setar número de vit, emp, der
        vitoria.setText(""+cursorTime.getInt(cursorTime.getColumnIndex("vitoria")));
        derrota.setText(""+cursorTime.getInt(cursorTime.getColumnIndex("derrota")));
        empate.setText(""+cursorTime.getInt(cursorTime.getColumnIndex("empate")));

        // Incrementa ou decrementa vitórias, derrotas, empates
        plusVit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarTV(1);
            }
        });
        plusDerrota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarTV(2);
            }
        });
        plusEmpate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarTV(3);
            }
        });
        minusVit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementarTV(1);
            }
        });
        minusDerrota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementarTV(2);
            }
        });
        minusEmpate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementarTV(3);
            }
        });

        // Salva os dados da equipe caso o número de vit + emp + derrota seja igual número de rodadas
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vit, der, emp;
                vit = Integer.parseInt(vitoria.getText().toString());
                der = Integer.parseInt(derrota.getText().toString());
                emp = Integer.parseInt(empate.getText().toString());
                if(vit+der+emp == numeroRodadas){
                    bd.execSQL("UPDATE times SET vitoria = '"+vit+"', derrota = '"+der+"', empate = '"+emp+"' WHERE id = '"+timeSelecionado+"'");
                    voltarTimes(v,1);
                } else {
                    Toast.makeText(getApplicationContext(),"Número de vitóras + empates + derrotas deve ser igual o número de rodadas ("+numeroRodadas+")!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Apenas volta para activity anterior
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTimes(v,0);
            }
        });

    }

    public void incrementarTV(int campo){
        int vit, der, emp;
        vit = Integer.parseInt(vitoria.getText().toString());
        der = Integer.parseInt(derrota.getText().toString());
        emp = Integer.parseInt(empate.getText().toString());
        if(vit+der+emp < numeroRodadas){
            int valorCampo = 0;
            if(campo == 1) {
                valorCampo = Integer.parseInt(vitoria.getText().toString());
                valorCampo++;
                vitoria.setText(valorCampo+"");
            } else if(campo == 2){
                valorCampo = Integer.parseInt(derrota.getText().toString());
                valorCampo++;
                derrota.setText(valorCampo+"");
            } else if(campo == 3){
                valorCampo = Integer.parseInt(empate.getText().toString());
                valorCampo++;
                empate.setText(valorCampo+"");
            }

        }
    }

    public void decrementarTV(int campo){
        int vit, der, emp;
        vit = Integer.parseInt(vitoria.getText().toString());
        der = Integer.parseInt(derrota.getText().toString());
        emp = Integer.parseInt(empate.getText().toString());
            int valorCampo = 0;
            if(campo == 1 && vit > 0) {
                valorCampo = Integer.parseInt(vitoria.getText().toString());
                valorCampo--;
                vitoria.setText(valorCampo+"");
            } else if(campo == 2 && der > 0){
                valorCampo = Integer.parseInt(derrota.getText().toString());
                valorCampo--;
                derrota.setText(valorCampo+"");
            } else if(campo == 3 && emp > 0){
                valorCampo = Integer.parseInt(empate.getText().toString());
                valorCampo--;
                empate.setText(valorCampo+"");
            }

    }

    // Envia o parâmetro que indica se o time foi ou não editado
    public void voltarTimes(View v, int check){
        Intent intent = new Intent(this, estatiscas_times.class);
        intent.putExtra("numeroRodadas",numeroRodadas);
        if(check == 1)
            intent.putExtra("edicaoTime",1);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    public void carregarEscudo(){
        int id = cursorTime.getInt(cursorTime.getColumnIndex("id"));
        escudo = findViewById(R.id.ivEscudoTimeEdicao);
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
