package br.dev.saipe.theimpostor.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.dev.saipe.theimpostor.dao.BancoHelper;
import br.dev.saipe.theimpostor.model.Tema;

public class TemaDAO {

    private SQLiteDatabase db;

    public TemaDAO(Context context) {
        BancoHelper helper = new BancoHelper(context);
        db = helper.getWritableDatabase();
    }

    public void inserirTema(Tema tema) {
        ContentValues valores = new ContentValues();
        valores.put("nome", tema.getNome());
        db.insert("tema", null, valores);
    }

    public ArrayList<Tema> listarTemas() {
        ArrayList<Tema> lista = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tema", null);
        if (c.moveToFirst()) {
            do {
                Tema t = new Tema();
                t.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                t.setNome(c.getString(c.getColumnIndexOrThrow("nome")));
                lista.add(t);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }
}
