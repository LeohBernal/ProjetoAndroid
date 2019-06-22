package com.example.brasfutero.model;

public class Jogadores {
    private String nome;
    private String posicao;
    private int idade;
    private String nacionalidade;

    public Jogadores(String nome, String posicao, int idade, String nacionalidade, int id_time) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
