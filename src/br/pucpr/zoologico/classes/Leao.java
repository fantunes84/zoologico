package br.pucpr.zoologico.classes;

import javax.swing.JOptionPane;

public class Leao extends Animal {

    public Leao(String nome, int idade, String sexo) {
        super(nome, idade, sexo, "Leao");
    }
    @Override
    public Boolean ehAdulto() {
        return this.idade >= 3;
    }
    @Override
    public void emitirBarulho() {
        if(estaVivo) {
            String mensagem = "";
            mensagem += String.format("%s está rugindo:\n", this.getNome());
            mensagem += "RRROOOOOOOOAAAAARRR";
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            JOptionPane.showMessageDialog(null, "O animal morreu.");
        }
    }
}
