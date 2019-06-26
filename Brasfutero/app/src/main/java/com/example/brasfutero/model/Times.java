package com.example.brasfutero.model;

import android.widget.ImageView;

public class Times {
    private String nome;
    private String tecnico;
    private int id;
    private int escudo;
    private int vitoria, derrota, empate;

    public Times(String nome, String tecnico, int id, int escudo, int vitoria, int derrota, int empate) {
        this.nome = nome;
        this.tecnico = tecnico;
        this.id = id;
        this.escudo = escudo;
        this.vitoria = vitoria;
        this.derrota = derrota;
        this.empate = empate;
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

    public int getVitoria() {
        return vitoria;
    }

    public void setVitoria(int vitoria) {
        this.vitoria = vitoria;
    }

    public int getDerrota() {
        return derrota;
    }

    public void setDerrota(int derrota) {
        this.derrota = derrota;
    }

    public int getEmpate() {
        return empate;
    }

    public void setEmpate(int empate) {
        this.empate = empate;
    }

    @Override
    public String toString() {
        return nome;
    }
}
