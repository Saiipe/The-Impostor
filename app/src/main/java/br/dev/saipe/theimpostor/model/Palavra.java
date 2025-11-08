package br.dev.saipe.theimpostor.model;

public class Palavra {
    private int id;
    private String palavra;
    private String dica;
    private int temaId;

    public Palavra() {}

    public Palavra(String palavra, String dica, int temaId) {
        this.palavra = palavra;
        this.dica = dica;
        this.temaId = temaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public int getTemaId() {
        return temaId;
    }

    public void setTemaId(int temaId) {
        this.temaId = temaId;
    }

    @Override
    public String toString() {
        return palavra + " (" + dica + ")";
    }
}