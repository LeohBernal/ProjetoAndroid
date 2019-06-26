package com.example.brasfutero.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.brasfutero.R;
import com.example.brasfutero.activity.escalacao;
import com.example.brasfutero.activity.editar_time;
import com.example.brasfutero.model.Times;

import java.util.ArrayList;
import java.util.List;

public class adapterTimes extends RecyclerView.Adapter<adapterTimes.MyViewHolder>{
    private List<Times> listaTimes;
    private int numeroRodadas;
    private Activity activity;
    public adapterTimes(List<Times> listaTimes, int numeroRodadas, Activity activity){
        this.listaTimes = new ArrayList<Times>();
        this.listaTimes = listaTimes;
        this.numeroRodadas = numeroRodadas;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listaItens = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_listatimes, viewGroup, false);
        return new MyViewHolder(listaItens);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        double aproveitamento, pontosTotais;
        pontosTotais = numeroRodadas*3;
        aproveitamento = ((listaTimes.get(i).getVitoria()*3+listaTimes.get(i).getEmpate())/pontosTotais)*100;
        myViewHolder.nome.setText(listaTimes.get(i).getNome());
        myViewHolder.tecnico.setText(listaTimes.get(i).getTecnico());
        myViewHolder.vitoria.setText(listaTimes.get(i).getVitoria()+"");
        myViewHolder.derrota.setText(listaTimes.get(i).getDerrota()+"");
        myViewHolder.empate.setText(listaTimes.get(i).getEmpate()+"");
        myViewHolder.aproveitamento.setText(String.format("%.2f", aproveitamento)+" %");
        carregarEscudo(myViewHolder,listaTimes.get(i).getId());

        myViewHolder.jogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, escalacao.class);
                intent.putExtra("idTime", listaTimes.get(i).getId());
                intent.putExtra("numeroRodadas",numeroRodadas);
                activity.startActivity(intent);
            }
        });

        myViewHolder.editarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, editar_time.class);
                intent.putExtra("idTime", listaTimes.get(i).getId());
                intent.putExtra("numeroRodadas",numeroRodadas);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaTimes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //cria elementos gráficos que estarão no modelo
        TextView nome, tecnico, vitoria, derrota, empate, aproveitamento;
        ImageView escudo;
        LinearLayout jogadores, editarTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.tvNomeTime);
            tecnico = itemView.findViewById(R.id.tvNomeTecnico);
            tecnico = itemView.findViewById(R.id.tvNomeTecnico);
            vitoria = itemView.findViewById(R.id.tvNumVit);
            derrota = itemView.findViewById(R.id.tvNumDerrota);
            empate = itemView.findViewById(R.id.tvNumEmpate);
            aproveitamento = itemView.findViewById(R.id.tvNumAproveit);
            escudo = itemView.findViewById(R.id.ivTimeSelecao);
            jogadores = itemView.findViewById(R.id.llVerificarJogador);
            editarTime = itemView.findViewById(R.id.llEditarTime);

        }
    }

    public void carregarEscudo(MyViewHolder myViewHolder, int id){
        switch (id){
            case 1:
                myViewHolder.escudo.setImageResource(R.drawable.santos);
                break;
            case 2:
                myViewHolder.escudo.setImageResource(R.drawable.palmeiras);
                break;
            case 3:
                myViewHolder.escudo.setImageResource(R.drawable.corinthians);
                break;
            case 4:
                myViewHolder.escudo.setImageResource(R.drawable.saopaulo);
                break;
            case 5:
                myViewHolder.escudo.setImageResource(R.drawable.flamengo);
                break;
            case 6:
                myViewHolder.escudo.setImageResource(R.drawable.fluminense);
                break;
            case 7:
                myViewHolder.escudo.setImageResource(R.drawable.vasco);
                break;
            case 8:
                myViewHolder.escudo.setImageResource(R.drawable.botafogo);
                break;
            case 9:
                myViewHolder.escudo.setImageResource(R.drawable.cruzeiro);
                break;
            case 10:
                myViewHolder.escudo.setImageResource(R.drawable.atleticomg);
                break;
            case 11:
                myViewHolder.escudo.setImageResource(R.drawable.internacional);
                break;
            case 12:
                myViewHolder.escudo.setImageResource(R.drawable.gremio);
                break;
            case 13:
                myViewHolder.escudo.setImageResource(R.drawable.bahia);
                break;
            case 14:
                myViewHolder.escudo.setImageResource(R.drawable.ceara);
                break;
            case 15:
                myViewHolder.escudo.setImageResource(R.drawable.fortaleza);
                break;
            case 16:
                myViewHolder.escudo.setImageResource(R.drawable.athleticopr);
                break;
            case 17:
                myViewHolder.escudo.setImageResource(R.drawable.goias);
                break;
            case 18:
                myViewHolder.escudo.setImageResource(R.drawable.chapecoense);
                break;
            case 19:
                myViewHolder.escudo.setImageResource(R.drawable.avai);
                break;
            case 20:
                myViewHolder.escudo.setImageResource(R.drawable.csa);
                break;
        }
    }

}
