package com.example.brasfutero.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.brasfutero.model.Times;
import java.util.ArrayList;
import java.util.List;
import com.example.brasfutero.R;

public class editor_jogo extends AppCompatActivity {
    private RecyclerView recyclerDados;
    private List<Times> times = new ArrayList<Times>();
    private SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_jogo);
        recyclerDados = findViewById(R.id.rvTimes);
        Cursor cursor = bd.rawQuery("SELECT * FROM times", null);
        for(int i = 0; i < cursor.getCount(); i++){

        }
    }

    public void gerenciarTime(View view){
        Intent intent = new Intent(this, novo_time.class);
        startActivity(intent);
    }

}
