package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brasfutero.model.Times;
import java.util.ArrayList;
import java.util.List;
import com.example.brasfutero.R;

public class editor_jogo extends AppCompatActivity {
    private RecyclerView recyclerDados;
    private List<Times> times = new ArrayList<Times>();
    private SQLiteDatabase bd;
    private Button rodada;
    private EditText numRodadas;
    private Cursor cursorRodadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_jogo);
        bd = openOrCreateDatabase("banco8",MODE_PRIVATE,null);
        rodada = findViewById(R.id.btnAtualizarRodadas);
        numRodadas = findViewById(R.id.etNumRodadas);

        cursorRodadas = bd.rawQuery("SELECT * FROM rodadas",null);
        cursorRodadas.moveToFirst();

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

    public void gerenciarTime(View view){
        Intent intent = new Intent(this, inicio_jogo.class);
        startActivity(intent);
    }

}
