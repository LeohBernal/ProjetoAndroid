package com.example.brasfutero.adapter;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.brasfutero.R;
import com.example.brasfutero.model.Times;

import java.util.ArrayList;
import java.util.List;

public class adapterTimes extends RecyclerView.Adapter<adapterTimes.MyViewHolder>{
    private List<Times> listaTimes;
    private SQLiteDatabase bd;
    public adapterTimes(List<Times> listaTimes){
        this.listaTimes = new ArrayList<Times>();
        this.listaTimes = listaTimes;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listaItens = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_listatimes, viewGroup, false);
        return new MyViewHolder(listaItens);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nome.setText(listaTimes.get(i).getNome());
        myViewHolder.tecnico.setText(listaTimes.get(i).getTecnico());
        carregarEscudo(myViewHolder,listaTimes.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return listaTimes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //cria elementos gráficos que estarão no modelo
        TextView nome, tecnico;
        ImageView escudo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.tvNomeTime);
            tecnico = itemView.findViewById(R.id.tvNomeTecnico);
            escudo = itemView.findViewById(R.id.ivTimeSelecao);

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
