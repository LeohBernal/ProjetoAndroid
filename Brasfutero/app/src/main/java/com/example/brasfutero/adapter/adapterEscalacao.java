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
    private int numeroRodadas;
    private List<Jogadores> listaJogadores;

    public adapterEscalacao(List<Jogadores> listaJogadores, int numeroRodadas){
        this.listaJogadores = new ArrayList<Jogadores>();
        this.listaJogadores = listaJogadores;
        this.numeroRodadas = numeroRodadas;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listaItens = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_listajogadores, viewGroup, false);
        return new MyViewHolder(listaItens);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        double mediaGols, mediaAssist, mediaCA, mediaCV;
        System.out.println("NUMERO DE RODADAS: "+numeroRodadas);
        mediaGols = (double) listaJogadores.get(i).getGols()/ (double) numeroRodadas;
        mediaAssist = (double) listaJogadores.get(i).getAssistencia() / (double) numeroRodadas;
        mediaCA = (double) listaJogadores.get(i).getCA() / (double) numeroRodadas;
        mediaCV = (double) listaJogadores.get(i).getCV() / (double) numeroRodadas;
        myViewHolder.nome.setText(listaJogadores.get(i).getNome());
        myViewHolder.posicao.setText(listaJogadores.get(i).getPosicao());
        myViewHolder.nacionalidade.setText(listaJogadores.get(i).getNacionalidade());
        myViewHolder.idade.setText(""+listaJogadores.get(i).getIdade());
        myViewHolder.gol.setText(""+listaJogadores.get(i).getGols());
        myViewHolder.assistencia.setText(""+listaJogadores.get(i).getAssistencia());
        myViewHolder.CA.setText(""+listaJogadores.get(i).getCA());
        myViewHolder.CV.setText(""+listaJogadores.get(i).getCV());
        myViewHolder.mediaGol.setText(""+String.format("%.2f", mediaGols));
        myViewHolder.mediaAssist.setText(""+String.format("%.2f", mediaAssist));
        myViewHolder.mediaCA.setText(""+(String.format("%.2f", mediaCA)));
        myViewHolder.mediaCV.setText(""+(String.format("%.2f", mediaCV)));
    }

    @Override
    public int getItemCount() {
        return listaJogadores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //cria elementos gráficos que estarão no modelo
        TextView nome, posicao, nacionalidade, idade, gol, assistencia, CA, CV;
        TextView mediaGol, mediaAssist, mediaCA, mediaCV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.tvNomeJogador);
            posicao = itemView.findViewById(R.id.tvPosicao);
            nacionalidade = itemView.findViewById(R.id.tvNacionalidade);
            idade = itemView.findViewById(R.id.tvIdade);
            gol = itemView.findViewById(R.id.tvGols);
            assistencia = itemView.findViewById(R.id.tvAssistencias);
            CA = itemView.findViewById(R.id.tvCA);
            CV = itemView.findViewById(R.id.tvCV);
            mediaGol = itemView.findViewById(R.id.tvMediaGols);
            mediaAssist = itemView.findViewById(R.id.tvMediaAssist);
            mediaCA = itemView.findViewById(R.id.tvMediaCA);
            mediaCV = itemView.findViewById(R.id.tvMediaCV);
        }
    }


}