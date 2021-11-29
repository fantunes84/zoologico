package br.pucpr.zoologico.classes;

import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Zoologico {
    private String nome;
    public List<Animal> animais;
    public Veterinario veterinario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Zoologico(String nome, Veterinario veterinario) {
        this.nome = nome;
        this.animais = new ArrayList<Animal>();
        this.veterinario = veterinario;
    }

    public void adicionarAnimal(Animal animalASerAdicionado) {
        this.animais.add(animalASerAdicionado);
    }

    private boolean intValido(String s) {
        try {
            Integer.parseInt(s); //Método estático, que tenta tranformar uma string em inteiro
            return true;
        } catch (NumberFormatException e) { // Não conseguiu transformar em inteiro e gera erro
            return false;
        }
    }
    
    public int verificarOpcoesValidas(int valor, int minimo, int maximo) {
        while(valor > maximo || valor < minimo) {
            String mensagem = String.format("Valor incorreto!\n\nDigite um número maior que %d e menor que %d", minimo -1, maximo +1);
            String resp = JOptionPane.showInputDialog(mensagem);
            valor = this.retornaInteiro(resp);
        }
        return valor;
    }
    
    public int retornaInteiro(String entrada) { // retorna um valor inteiro
        int numInt;

        // Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
        while (!this.intValido(entrada)) {
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }
        return Integer.parseInt(entrada);
    }

    public void setVeterinario(Veterinario vet) {
        this.veterinario = vet;
        System.out.print(veterinario.getNome());
    }
    
    public void selecionarAnimal() {
        String animaisStr = "";
        int contador = 0;
        String menu, entrada1, entrada2 = "";
        int opcao1, opcao2, entrada1Int;

        for (Animal animal : this.animais) {
            animaisStr += String.format("%d - %s.\n", contador, animal.getNome());
            contador ++;
        }
        entrada1 = JOptionPane.showInputDialog("Escolha um dos animais:\n\n" + animaisStr);
        entrada1Int = this.retornaInteiro(entrada1);
        opcao1 = this.verificarOpcoesValidas(entrada1Int, 0, this.animais.size() - 1);
        Animal animal = this.animais.get(opcao1);

        menu = "Escolha uma opção\n" +
                "1. Fazer barulho\n" +
                "2. Chamar veterinário\n" +
                "3. Remover Animal\n";

        entrada2 = JOptionPane.showInputDialog(menu +"\n\n");
        opcao2 = this.retornaInteiro(entrada2);
        switch (opcao2) {
            case 1:
                animal.emitirBarulho();
                break;
            case 2:
            	if (animal.estaVivo) {
            		if (animal.estaDoente) {
                        veterinario.atenderAnimal(animal);
                        double numero = Math.random();
                        boolean curou = numero < 0.7;
                        if (curou) {
                            animal.serCurado();
                            String mensagem = String.format("O veterinario %s curou %s.",  veterinario.getNome(), animal.getNome());
                            JOptionPane.showMessageDialog(null, mensagem);
                        } else {
                            animal.morrer();
                            String mensagem = String.format("O veterinario %s não conseguiu curar %s, que acabou morrendo.", veterinario.getNome(), animal.getNome());
                            JOptionPane.showMessageDialog(null, mensagem);
                        }
                    } else {
                        String mensagem = String.format("%s não está doente.", animal.getNome());
                        JOptionPane.showMessageDialog(null, mensagem);
                    }
            	} else {
            		JOptionPane.showMessageDialog(null, animal.toString());
            	}
                
                break;
            case 3:
                this.removerAnimal(animal);
                break;
        }
    }
    
    public void salvarZoologico() {
    	try {
    		File arquivo = new File("zoologico.txt");
            if (!arquivo.exists()) {
            	arquivo.createNewFile();
            } else {
            	arquivo.delete();
            }
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Animal animal : this.animais) {
            	bw.write(String.format("%s;%d;%s;%s\n", 
            			animal.getNome(), animal.getIdade(), animal.getSexo(), animal.getEspecie()));
            }
        	bw.close();
        	fw.close();
            JOptionPane.showMessageDialog(null,"Animais cadastrados gravados com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu.");
            e.printStackTrace();
        } 
    }
    
    public void recuperarZoologico() {
    	try {
    		if (this.animais.size() > 0) {
    			this.animais.clear();
    		}
    		File arquivo = new File("zoologico.txt");
	    	FileReader fr = new FileReader(arquivo);
	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	while (br.ready()) {
		    	String linha = br.readLine();
		    	String[] atributos = linha.split(";");
		    	this.animais.add(new Leao(atributos[0], Integer.parseInt(atributos[1]), atributos[2]));
	    	}
	    	br.close();
	    	fr.close();
    	} catch (IOException ex) {
    	ex.printStackTrace();
    	}
    }
    
    public void ficarDoente() {
        Random random = new Random();
        int numero = random.nextInt(this.animais.size());
        Animal animal = this.animais.get(numero);
        animal.ficarDoente();
        String mensagem = String.format("%s está doente. Se não for curado pode morrer.", animal.getNome());
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    public void removerAnimal(Animal animal) {
        for (int i=0; i < this.animais.size(); i++) {
            Animal animalLista = this.animais.get(i);

            if (animalLista.equals(animal)) {
                this.animais.remove(i);
                String mensagem = String.format("%s foi removido:\n", animal.getNome());
                JOptionPane.showMessageDialog(null, mensagem);
            }
        }
    }
    public void listarAnimais() {
        String dados = "";
        dados += String.format("Animais do %s:\n",  this.nome);
        for (int i=0; i < this.animais.size(); i++) {
            dados += this.animais.get(i).toString() + "---------------\n";
        }
        JOptionPane.showMessageDialog(null, dados);
    }
}
