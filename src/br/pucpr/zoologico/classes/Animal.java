package br.pucpr.zoologico.classes;

public abstract class Animal implements Animavel {
    private String nome;
    private String especie;
    private String sexo;
    protected int idade;
    protected Boolean estaDoente;
    protected Boolean estaVivo;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getSexo() {
        return sexo;
    }
    public void getSexo(String sexo) {
        this.sexo = sexo;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public Boolean estaDoente() {
        return estaDoente();
    }
    public Boolean estaVivo() {
        return estaVivo;
    }
    // Construtor personalizado. Faz a vez do _init_ do python
    public Animal(String nome, int idade, String sexo, String especie) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.especie = especie;
        this.estaDoente = false;
        this.estaVivo = true;
    }

    @Override
    public abstract void emitirBarulho();

    @Override
    public Boolean ehAdulto() {
        return idade >= 2;
    }
    @Override
    public final void ficarDoente() {
        this.estaDoente = true;
    }
    @Override
    public final void serCurado() {
        this.estaDoente = false;
    }
    @Override
    public final void morrer() {
        this.estaVivo = false;
    }
    private String retornaSimOuNao(Boolean retorno) {
        if (retorno) {
            return "Sim";
        } else {
            return "Não";
        }
    }
    public String toString() {
        if (this.estaVivo){
            String retorno = "";
            String doente = retornaSimOuNao(estaDoente);
            String adulto = retornaSimOuNao(ehAdulto());

            retorno += String.format("Nome: %s.\n", this.nome);
            retorno += String.format("Idade: %d anos.\n", this.idade);
            retorno += String.format("Espécie: %s.\n", this.especie);
            retorno += String.format("Sexo %s.\n",this.sexo);
            retorno += String.format("É adulto: %s\n", adulto);
            retorno += String.format("Está doente: %s\n", doente);
            return retorno;
        } else {
            String retorno = "";
            String doente = retornaSimOuNao(estaDoente);
            String adulto = retornaSimOuNao(ehAdulto());

            retorno += String.format("Nome: %s faleceu.\n", this.nome);
            retorno += "Favor remover o animal do zoológico.\n";
            return retorno;
        }
    }
}

