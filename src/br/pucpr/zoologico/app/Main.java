package br.pucpr.zoologico.app;

import br.pucpr.zoologico.classes.*;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // Instanciando zoologico
    public static Veterinario veterinario = new Veterinario();
    public static Zoologico zoologico = new Zoologico("Zoo da PUC", veterinario);
    
    public static List<String> lerTeclado() {
    	List<String> entradas = new ArrayList<String>();
        String nome = JOptionPane.showInputDialog("Digite o nome do animal:");
        String idade = JOptionPane.showInputDialog("Digite a idade do animal:");
        String sexo = "";
        String especie = "";
        
        String mensagemSexo = "Digite o sexo do animal:\n\n"
                + "1 - Macho.\n"
                + "2 - Fêmea";
        String sexoResposta = JOptionPane.showInputDialog(mensagemSexo);
        int intSexoresposta = zoologico.retornaInteiro(sexoResposta);
        
        if (zoologico.verificarOpcoesValidas(intSexoresposta, 1, 2) == 1) {
            sexo = "Macho";
        } else if (zoologico.verificarOpcoesValidas(intSexoresposta, 1, 2) == 2) {
            sexo = "Fêmea";
        }
        
        String mensagemEspecie = "Digite o sexo do leão:\n\n"
                + "1 - Leão.\n"
                + "2 - Hiena.\n"
                + "3 - Macaco.\n"
                + "4 - Tucano.\n";
        String especieResposta = JOptionPane.showInputDialog(mensagemEspecie);
        int especieInt = zoologico.retornaInteiro(especieResposta);
        int especieIntVerificada = zoologico.verificarOpcoesValidas(especieInt, 1, 4);
        
        //int idade = zoologico.retornaInteiro(idadeStr);
        entradas.add(nome);
        entradas.add(idade);
        entradas.add(sexo);
        entradas.add(String.format("%d", especieIntVerificada));
        
        return entradas;
    }
    
    public static Animal montarObjetoAnimal(String nome, String idadeStr, String sexo, String especieStr) {
    	int idade = zoologico.retornaInteiro(idadeStr);
    	int especie = zoologico.retornaInteiro(especieStr) ;
    	
    	if (especie == 1) {
    		Animal animal = new Leao(nome, idade, sexo);
    		return animal;
    	} else if (especie == 2) {
    		Animal animal = new Hiena(nome, idade, sexo);
    		return animal;
    	} else if (especie == 3) {
    		Animal animal = new Macaco(nome, idade, sexo);
    		return animal;
    	}else {
    		Animal animal = new Tucano(nome, idade, sexo);
    		return animal;
    	}
    }
    
    public static void iniciarMenu() {
        String menu = "";
        String entrada;
        int opc1, opc2, indice;
        do {
            menu = zoologico.getNome() + "\n" +
                    "Opções:\n" +
                    "1. Cadastrar Animal\n" +
                    "2. Exibir Animais\n" +
                    "3. Selecionar Animal\n" +
                    "4. Limpar Animais\n" +
                    "5. Gravar Animais\n" +
                    "6. Recuperar animais  \n" +
                    "7. Animal fica doente (randômico)\n" +
                    "8. Animais tratados pelo veterinário.\n" +
                    "0. Sair";
            entrada = JOptionPane.showInputDialog(menu + "\n\n");
            opc1 = zoologico.retornaInteiro(entrada);
            switch (opc1) {
                case 1:
                	List<String> entradas = lerTeclado();
                    zoologico.adicionarAnimal(montarObjetoAnimal(entradas.get(0), entradas.get(1), entradas.get(2), entradas.get(3)));
                    break;
                case 2:
                    if (zoologico.animais.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhum animal cadastrado. Cadastre algum.");
                        break;
                    }
                    zoologico.listarAnimais();
                    break;
                case 3:
                if (zoologico.animais.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum animal cadastrado. Cadastre algum.");
                    break;
                }
                zoologico.selecionarAnimal();
                break;
                case 4:
                    if (zoologico.animais.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhum animal cadastrado. Cadastre Algum.");
                        break;
                    }
                    zoologico.animais.clear();
                    JOptionPane.showMessageDialog(null, "Dados LIMPOS com sucesso!");
                    break;
                case 5:
                    zoologico.salvarZoologico();
                    break;
                case 6:
            		zoologico.recuperarZoologico();
                    break;
                case 7:
                    zoologico.ficarDoente();
                    break;
                case 8:
                    veterinario.listarAnimaisAtentidos();
            }
        } while (opc1 != 0);
    }
    public static void main(String[] args) {
        //String vetName = JOptionPane.showInputDialog("Digite o nome do veterinário: ");
    	String vetName = "Paulo";
        veterinario.setNome(vetName);
        
        // Recuperando a lista de animais, caso já esteja salva
        zoologico.recuperarZoologico();
        
        iniciarMenu();
    }
}
