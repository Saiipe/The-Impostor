package br.dev.saipe.theimpostor.model;

public class Jogador {

    private int id;
    private String nome;
    private int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao(){
        return pontuacao;
    }

    public void setPontuacao(int pontuacao){
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString() {
        return "Nome:" + nome + " Pontuação: " + pontuacao ;
    }
}
