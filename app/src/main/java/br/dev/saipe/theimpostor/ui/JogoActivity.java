package br.dev.saipe.theimpostor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import br.dev.saipe.theimpostor.R;
import br.dev.saipe.theimpostor.logic.IniciandoJogo;
import br.dev.saipe.theimpostor.model.Jogador;
import br.dev.saipe.theimpostor.model.Palavra;

public class JogoActivity extends AppCompatActivity {

    private Button btnFinalizar;
    private LinearLayout containerJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        // Inicializa componentes
        btnFinalizar = findViewById(R.id.btnFinalizar);
        containerJogadores = findViewById(R.id.containerJogadores);

        // Recupera o tema selecionado da tela anterior
        String temaSelecionado = getIntent().getStringExtra("temaSelecionado");

        if (temaSelecionado == null) {
            Toast.makeText(this, "Tema não recebido!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Inicializa a lógica do jogo
        IniciandoJogo iniciandoJogo = new IniciandoJogo(this);
        ArrayList<Jogador> jogadores = iniciandoJogo.getListaJogadores();
        Jogador impostor = iniciandoJogo.getImpostor();

        Palavra palavraSorteada = iniciandoJogo.getPalavraPorTema(temaSelecionado);

        if (palavraSorteada == null) {
            Toast.makeText(this, "Nenhuma palavra encontrada para o tema selecionado!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        String palavra = palavraSorteada.getPalavra();
        String dica = palavraSorteada.getDica();

        // Cria um card para cada jogador
        for (Jogador jogador : jogadores) {
            CardView card = new CardView(this);
            card.setCardBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            card.setRadius(24f);
            card.setCardElevation(8f);
            card.setUseCompatPadding(true);
            card.setContentPadding(40, 40, 40, 40);

            LinearLayout layoutInterno = new LinearLayout(this);
            layoutInterno.setOrientation(LinearLayout.VERTICAL);
            layoutInterno.setGravity(android.view.Gravity.CENTER_HORIZONTAL);

            TextView nomeJogador = new TextView(this);
            nomeJogador.setText(jogador.getNome());
            nomeJogador.setTextColor(getResources().getColor(android.R.color.white));
            nomeJogador.setTextSize(20);
            nomeJogador.setPadding(0, 0, 0, 10);

            TextView textoOculto = new TextView(this);
            textoOculto.setTextColor(getResources().getColor(android.R.color.white));
            textoOculto.setVisibility(View.GONE); // Esconde até o jogador clicar

            layoutInterno.addView(nomeJogador);
            layoutInterno.addView(textoOculto);
            card.addView(layoutInterno);

            // Define comportamento ao clicar no card
            card.setOnClickListener(v -> {
                if (textoOculto.getVisibility() == View.GONE) {
                    if (jogador.getId() == impostor.getId()) {
                        textoOculto.setText("💡 Dica: " + dica);
                    } else {
                        textoOculto.setText("📝 Palavra: " + palavra);
                    }
                    textoOculto.setVisibility(View.VISIBLE);
                } else {
                    textoOculto.setVisibility(View.GONE);
                }
            });

            // Adiciona o card ao container principal
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 30);
            card.setLayoutParams(params);

            containerJogadores.addView(card);
        }

        // Botão de finalizar o jogo
        btnFinalizar.setOnClickListener(v -> {
            Intent intent = new Intent(JogoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
