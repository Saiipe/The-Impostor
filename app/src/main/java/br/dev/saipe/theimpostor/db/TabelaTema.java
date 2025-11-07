package br.dev.saipe.theimpostor.db;

public class TabelaTema {
    public static final String NOME_TABELA = "tema";

    public static final String COL_ID = "id";
    public static final String COL_NOME = "nome";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOME + " TEXT NOT NULL" +
                    ")";
}
