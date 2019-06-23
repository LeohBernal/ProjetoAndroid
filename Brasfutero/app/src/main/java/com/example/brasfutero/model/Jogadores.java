package com.example.brasfutero.model;

public class Jogadores {
    private String nome;
    private String posicao;
    private int idade, gols, assistencia, CA, CV, id;
    private String nacionalidade;

    public Jogadores(String nome, String posicao, int idade, int gols, int assistencia, int CA, int CV, String nacionalidade, int id) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.gols = gols;
        this.assistencia = assistencia;
        this.CA = CA;
        this.CV = CV;
        this.nacionalidade = nacionalidade;
        this.id = id;
    }

    public Jogadores(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public int getAssistencia() {
        return assistencia;
    }

    public void setAssistencia(int assistencia) {
        this.assistencia = assistencia;
    }

    public int getCA() {
        return CA;
    }

    public void setCA(int CA) {
        this.CA = CA;
    }

    public int getCV() {
        return CV;
    }

    public void setCV(int CV) {
        this.CV = CV;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
