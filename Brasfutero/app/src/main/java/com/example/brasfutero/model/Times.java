package com.example.brasfutero.model;

import android.widget.ImageView;

public class Times {
    private String nome;
    private String tecnico;
    private int id;

    public Times(String nome, String tecnico, int id){
        this.nome = nome;
        this.tecnico = tecnico;
        this.id = id;
    }

    public Times() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome;
    }
}
