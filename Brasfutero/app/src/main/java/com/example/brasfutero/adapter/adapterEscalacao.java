package com.example.brasfutero.adapter;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brasfutero.R;
import com.example.brasfutero.model.Jogadores;

import java.util.ArrayList;
import java.util.List;

public class adapterEscalacao extends RecyclerView.Adapter<adapterEscalacao.MyViewHolder>{
    private List<Jogadores> listaJogadores;
    private SQLiteDatabase bd;

    public adapterEscalacao(List<Jogadores> listaJogadores){
        this.listaJogadores = new ArrayList<Jogadores>();
        this.listaJogadores = listaJogadores;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listaItens = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_listajogadores, viewGroup, false);
        return new MyViewHolder(listaItens);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nome.setText(listaJogadores.get(i).getNome());
        myViewHolder.posicao.setText(listaJogadores.get(i).getPosicao());
        myViewHolder.nacionalidade.setText(listaJogadores.get(i).getNacionalidade());
        myViewHolder.idade.setText(""+listaJogadores.get(i).getIdade());
    }

    @Override
    public int getItemCount() {
        return listaJogadores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //cria elementos gráficos que estarão no modelo
        TextView nome, posicao, nacionalidade, idade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.tvNomeJogador);
            posicao = itemView.findViewById(R.id.tvPosicao);
            nacionalidade = itemView.findViewById(R.id.tvNacionalidade);
            idade = itemView.findViewById(R.id.tvIdade);
        }
    }


}