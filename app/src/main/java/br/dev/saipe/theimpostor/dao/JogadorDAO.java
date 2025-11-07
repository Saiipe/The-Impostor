package br.dev.saipe.theimpostor.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import br.dev.saipe.theimpostor.model.Jogador;

public class JogadorDAO {

    private SQLiteDatabase db;

    public JogadorDAO(Context context){
        BancoHelper helper = new BancoHelper(context);
        db = helper.getWritableDatabase();
    }

    public void inserirJogador(Jogador jogador){
        ContentValues valores = new ContentValues();
        valores.put("nome", jogador.getNome());
        db.insert("jogador", null, valores);
    }

    public ArrayList<Jogador> listarJogador() {
        ArrayList<Jogador> lista = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM jogador", null);
        if (c.moveToFirst()) {
            do {
                Jogador j = new Jogador(
                        c.getString(c.getColumnIndexOrThrow("nome"))
                );
                j.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                lista.add(j);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }


}
