package br.pucpr.zoologico.classes;

import javax.swing.JOptionPane;

public class Hiena extends Animal {

    public Hiena(String nome, int idade, String sexo) {
        super(nome, idade, sexo, "Hiena");
    }
    
    @Override
    public void emitirBarulho() {
        if(estaVivo) {
            String mensagem = "";
            mensagem += String.format("%s está gargaleando:\n", this.getNome());
            mensagem += "ih ih ih ih ih";
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            JOptionPane.showMessageDialog(null, "O animal morreu.");
        }
    }
}