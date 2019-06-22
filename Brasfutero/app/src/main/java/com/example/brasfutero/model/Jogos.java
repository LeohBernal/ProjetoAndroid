package com.example.brasfutero.model;

public class Jogos {

    private Times mandante;
    private Times visitante;
    private String local;
    private int golM, golV;
    private boolean vitM, vitV, empate;

    public Jogos(Times mandante, Times visitante) {
        if (mandante != visitante) {
            this.mandante = mandante;
            this.visitante = visitante;
        }
    }

    public void resultadoJogo(int golM, int golV){
        this.golM = golM;
        this.golV = golV;

        if(golM>golV){ // condições para saber se o mandante/visitante ganhou ou houve empate
            vitM = true;
            vitV = false;
            empate = false;
        } else if(golM<golV){
            vitM = false;
            vitV = true;
            empate = false;
        } else {
            vitM = false;
            vitV = false;
            empate = true;
        }
    }

    public int getGolM() {
        return golM;
    }

    public int getGolV() {
        return golV;
    }

    public Times getMandante() {
        return mandante;
    }

    public Times getVisitante() {
        return visitante;
    }

}
