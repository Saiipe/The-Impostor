package br.dev.saipe.theimpostor.db;


public class TabelaPalavra {
    public static final String NOME_TABELA = "palavra";

    public static final String COL_ID = "id";
    public static final String COL_PALAVRA = "palavra";
    public static final String COL_DICA = "dica";
    public static final String COL_TEMA_ID = "tema_id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_PALAVRA + " TEXT NOT NULL, " +
                    COL_DICA + " TEXT, " +
                    COL_TEMA_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + COL_TEMA_ID + ") REFERENCES tema(id)" +
                    ")";
}