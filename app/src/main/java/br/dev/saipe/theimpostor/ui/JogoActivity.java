package br.dev.saipe.theimpostor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.dev.saipe.theimpostor.R;
import br.dev.saipe.theimpostor.logic.IniciandoJogo;
import br.dev.saipe.theimpostor.model.Jogador;

public class JogoActivity extends AppCompatActivity {

    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        IniciandoJogo iniciandoJogo = new IniciandoJogo(this);
        Jogador impostor = iniciandoJogo.getImpostor();

        // Só pra testar
        if (impostor != null) {
            Toast.makeText(this, "O impostor é: " + impostor.getNome(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Nenhum jogador encontrado!", Toast.LENGTH_SHORT).show();
        }

        String temaSelecionado = getIntent().getStringExtra("temaSelecionado");

        if (temaSelecionado != null) {
            Toast.makeText(this, "Tema recebido: " + temaSelecionado, Toast.LENGTH_SHORT).show();
            // aqui você pode usar o tema para configurar o jogo
        }

        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(v -> {
            Intent intent = new Intent(JogoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}