package com.example.brasfutero.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.brasfutero.R;

public class novo_time extends AppCompatActivity {
    private Button criarTime;
    private EditText nomeTime, nomeTecnico;
    private SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_time);


        bd = openOrCreateDatabase("bancoBrasfoot",MODE_PRIVATE,null);

        criarTime = findViewById(R.id.btnCriarTime);
        nomeTime = findViewById(R.id.etTime);
        nomeTecnico = findViewById(R.id.etTecnico);

        criarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nomeTecnico.getText().toString().isEmpty() && !nomeTime.getText().toString().isEmpty()){
                    String stringNomeTecnico, stringNomeTime;
                    stringNomeTime = nomeTime.getText().toString();
                    stringNomeTecnico = nomeTecnico.getText().toString();
                    bd.execSQL("INSERT INTO times(nome,tecnico) VALUES ('"+stringNomeTime+"','"+stringNomeTecnico+"')");
                } else {
                    // fazer alerta p/ preencher campo
                }
            }
        });
    }
}
