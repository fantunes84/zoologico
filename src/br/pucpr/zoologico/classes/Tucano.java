package br.pucpr.zoologico.classes;

import javax.swing.JOptionPane;

public class Tucano extends Animal {

    public Tucano(String nome, int idade, String sexo) {
        super(nome, idade, sexo, "Tucano");
    }
    @Override
    public Boolean ehAdulto() {
        return this.idade >= 4;
    }
    @Override
    public void emitirBarulho() {
        if(estaVivo) {
            String mensagem = "";
            mensagem += String.format("%s está gorgeando:\n", this.getNome());
            mensagem += "Tri tri tri";
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            JOptionPane.showMessageDialog(null, "O animal morreu.");
        }
    }
}