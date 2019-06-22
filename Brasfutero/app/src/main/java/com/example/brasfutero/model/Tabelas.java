package com.example.brasfutero.model;

public class Tabelas {
    private int id_time;
    private int pontos;

    public Tabelas(int id, int pontos) {
        this.id_time = id;
        this.pontos = pontos;
    }

    public Tabelas(){}

    public int getId() {
        return id_time;
    }

    public void setId(int id_time) {
        this.id_time = id_time;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
