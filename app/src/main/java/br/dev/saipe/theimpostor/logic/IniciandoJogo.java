package br.dev.saipe.theimpostor.logic;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

import br.dev.saipe.theimpostor.dao.JogadorDAO;
import br.dev.saipe.theimpostor.dao.PalavraDAO;
import br.dev.saipe.theimpostor.dao.TemaDAO;
import br.dev.saipe.theimpostor.model.Jogador;
import br.dev.saipe.theimpostor.model.Palavra;
import br.dev.saipe.theimpostor.model.Tema;

public class IniciandoJogo {

    private JogadorDAO jogadorDAO;
    private TemaDAO temaDAO;
    private PalavraDAO palavraDAO;

    private ArrayList<Jogador> listaJogadores;
    private Jogador impostor;
    private Context context;

    public IniciandoJogo(Context context) {
        this.context = context;

        jogadorDAO = new JogadorDAO(context);
        temaDAO = new TemaDAO(context);
        palavraDAO = new PalavraDAO(context);

        listaJogadores = jogadorDAO.listarJogador();
        sortearImpostor();
    }

    // Sorteia um impostor aleatoriamente
    private void sortearImpostor() {
        if (listaJogadores != null && !listaJogadores.isEmpty()) {
            Random random = new Random();
            impostor = listaJogadores.get(random.nextInt(listaJogadores.size()));
        }
    }

    // Retorna o impostor
    public Jogador getImpostor() {
        return impostor;
    }

    // Retorna a lista de jogadores
    public ArrayList<Jogador> getListaJogadores() {
        return listaJogadores;
    }

    // Retorna a palavra e dica de acordo com o tema
    public Palavra getPalavraPorTema(String nomeTema) {
        // Busca o tema pelo nome
        ArrayList<Tema> temas = temaDAO.listarTemas();
        Tema temaEncontrado = null;

        for (Tema t : temas) {
            if (t.getNome().equalsIgnoreCase(nomeTema)) {
                temaEncontrado = t;
                break;
            }
        }

        if (temaEncontrado == null) {
            return null; // tema não encontrado
        }

        // Busca palavras associadas a esse tema
        ArrayList<Palavra> palavras = palavraDAO.listarPorTema(temaEncontrado.getId());

        if (palavras == null || palavras.isEmpty()) {
            return null; // nenhuma palavra para esse tema
        }

        // Sorteia uma palavra dentro do tema
        Random random = new Random();
        return palavras.get(random.nextInt(palavras.size()));
    }
}
