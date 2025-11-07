package br.dev.saipe.theimpostor.db;

public class TabelaJogador {
    public static final String NOME_TABELA = "jogador";

    public static final String COL_ID = "id";
    public static final String COL_NOME = "nome";
    public static final String COL_PONTUACAO = "pontuacao";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOME + " TEXT NOT NULL, " +
                    COL_PONTUACAO + " INTEGER DEFAULT 0" +
                    ")";
}