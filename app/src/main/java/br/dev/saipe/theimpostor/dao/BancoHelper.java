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

        // Inserir temas
        db.execSQL("INSERT INTO tema (nome) VALUES ('Emoções');");
        db.execSQL("INSERT INTO tema (nome) VALUES ('Natureza');");
        db.execSQL("INSERT INTO tema (nome) VALUES ('Tecnologia');");
        db.execSQL("INSERT INTO tema (nome) VALUES ('Mitologia');");

        // Inserir palavras (tema_id = 1 => Emoções)
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (1, 'Medo', 'Escuridão');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (1, 'Ódio', 'Fúria');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (1, 'Alegria', 'Sol');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (1, 'Tristeza', 'Chuva');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (1, 'Calma', 'Silêncio');");

        // Tema 2 => Natureza
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (2, 'Fogo', 'Calor');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (2, 'Vento', 'Movimento');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (2, 'Rocha', 'Força');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (2, 'Mar', 'Profundidade');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (2, 'Sol', 'Luz');");

        // Tema 3 => Tecnologia
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (3, 'Código', 'Linguagem');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (3, 'Rede', 'Conexão');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (3, 'Tela', 'Imagem');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (3, 'Chip', 'Processador');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (3, 'Dado', 'Informação');");

        // Tema 4 => Mitologia
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (4, 'Zeus', 'Trovão');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (4, 'Hades', 'Submundo');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (4, 'Afrodite', 'Beleza');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (4, 'Apolo', 'Luz');");
        db.execSQL("INSERT INTO palavra (tema_id, palavra, dica) VALUES (4, 'Hermes', 'Mensageiro');");

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