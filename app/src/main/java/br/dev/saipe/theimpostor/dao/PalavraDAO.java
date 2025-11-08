package br.dev.saipe.theimpostor.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.dev.saipe.theimpostor.dao.BancoHelper;
import br.dev.saipe.theimpostor.model.Palavra;

public class PalavraDAO {

    private SQLiteDatabase db;

    public PalavraDAO(Context context) {
        BancoHelper helper = new BancoHelper(context);
        db = helper.getWritableDatabase();
    }

    public ArrayList<Palavra> listarPalavras() {
        ArrayList<Palavra> lista = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM palavra", null);
        if (c.moveToFirst()) {
            do {
                Palavra p = new Palavra();
                p.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                p.setPalavra(c.getString(c.getColumnIndexOrThrow("palavra")));
                p.setDica(c.getString(c.getColumnIndexOrThrow("dica")));
                p.setTemaId(c.getInt(c.getColumnIndexOrThrow("tema_id")));
                lista.add(p);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public ArrayList<Palavra> listarPorTema(int temaId) {
        ArrayList<Palavra> lista = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM palavra WHERE tema_id = ?", new String[]{String.valueOf(temaId)});
        if (c.moveToFirst()) {
            do {
                Palavra p = new Palavra();
                p.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                p.setPalavra(c.getString(c.getColumnIndexOrThrow("palavra")));
                p.setDica(c.getString(c.getColumnIndexOrThrow("dica")));
                p.setTemaId(temaId);
                lista.add(p);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }
}
