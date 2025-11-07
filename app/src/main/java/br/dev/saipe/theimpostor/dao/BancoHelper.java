package br.dev.saipe.theimpostor.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.dev.saipe.theimpostor.db.TabelaJogador;
import br.dev.saipe.theimpostor.db.TabelaPalavra;
import br.dev.saipe.theimpostor.db.TabelaTema;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "impostor.db";
    private static final int VERSAO = 1;

    public BancoHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação das tabelas
        db.execSQL(TabelaJogador.CREATE_TABLE);
        db.execSQL(TabelaTema.CREATE_TABLE);
        db.execSQL(TabelaPalavra.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Remove as tabelas antigas se existirem
        db.execSQL("DROP TABLE IF EXISTS " + TabelaJogador.NOME_TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TabelaTema.NOME_TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TabelaPalavra.NOME_TABELA);

        // Recria as tabelas
        onCreate(db);
    }
}