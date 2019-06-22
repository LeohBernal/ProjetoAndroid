package com.example.brasfutero.adapter;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    }

    @Override
    public int getItemCount() {
        return listaTimes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //cria elementos gráficos que estarão no modelo
        TextView nome;
        Button editar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //linka os elementos do layout aos atributos da classe
            nome = itemView.findViewById(R.id.tvNomeTime);
            editar = itemView.findViewById(R.id.btnEditarTime);
        }
    }


}
