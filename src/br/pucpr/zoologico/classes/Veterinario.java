package br.pucpr.zoologico.classes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Veterinario {
    private String nome;
    private List<Animal> animaisAtendidos;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
        animaisAtendidos = new ArrayList<Animal>();
    }
    public void atenderAnimal(Animal animalASerAtentido) {
        animaisAtendidos.add(animalASerAtentido);
    }
    public void listarAnimaisAtentidos() {
        if (this.animaisAtendidos.size() > 0) {
            String dados = "";
            dados += String.format("Animais atendidos por %s:\n", this.nome);
            for (Animal animal : this.animaisAtendidos) {
            	dados += String.format("- %s.\n", animal.getNome());
            }
            JOptionPane.showMessageDialog(null, dados);
        } else {
            String message = String.format("Nenhum animal foi atentido por %s.", this.nome);
            JOptionPane.showMessageDialog(null, message);
        }
    }
}
