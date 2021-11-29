package br.pucpr.zoologico.classes;

import javax.swing.JOptionPane;

public class Macaco extends Animal {

    public Macaco(String nome, int idade, String sexo) {
        super(nome, idade, sexo, "Macaco");
    }
    
    @Override
    public void emitirBarulho() {
        if(estaVivo) {
            String mensagem = "";
            mensagem += String.format("%s está guinchando:\n", this.getNome());
            mensagem += "Ú ú á á";
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            JOptionPane.showMessageDialog(null, "O animal morreu.");
        }
    }
}