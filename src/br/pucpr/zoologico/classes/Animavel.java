package br.pucpr.zoologico.classes;

public interface Animavel {
    Boolean ehAdulto();
    void emitirBarulho();
    void ficarDoente();
    void serCurado();
    void morrer();
}
