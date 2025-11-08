package br.dev.saipe.theimpostor.logic;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.dev.saipe.theimpostor.dao.JogadorDAO;
import br.dev.saipe.theimpostor.model.Jogador;

public class IniciandoJogo {

    private JogadorDAO jogadorDAO;
    private ArrayList<Jogador> listaJogadores;
    private Jogador impostor;

    public IniciandoJogo(Context context) {
        Random random = new Random();

        jogadorDAO = new JogadorDAO(context);
        listaJogadores = jogadorDAO.listarJogador();

        if (listaJogadores.size() > 0) {
            int indexAleatorio = random.nextInt(listaJogadores.size());
            impostor = listaJogadores.get(indexAleatorio);
        }
    }

    public Jogador getImpostor() {
        return impostor;
    }

    public ArrayList<Jogador> getListaJogadores() {
        return listaJogadores;
    }




}