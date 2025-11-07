package br.dev.saipe.theimpostor.model;

public class Tema {
    private int id;
    private String nome;

    public Tema() {}

    public Tema(String nome) {
        this.nome = nome;
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


    @Override
    public String toString() {
        return "Tema: " + nome;
    }
}