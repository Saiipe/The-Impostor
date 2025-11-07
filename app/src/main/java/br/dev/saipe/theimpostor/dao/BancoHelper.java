package br.dev.saipe.theimpostor.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.dev.saipe.theimpostor.db.TabelaJogador;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "impostor.db";
    private static final int VERSAO = 1;

    public BancoHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TabelaJogador.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TabelaJogador.NOME_TABELA);
        onCreate(db);
    }
}
