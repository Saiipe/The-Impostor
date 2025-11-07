package br.dev.saipe.theimpostor.ui;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import br.dev.saipe.theimpostor.R;
import br.dev.saipe.theimpostor.dao.JogadorDAO;
import br.dev.saipe.theimpostor.model.Jogador;

public class MainActivity extends AppCompatActivity {

    private ListView listParticipantes;
    private Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listParticipantes = findViewById(R.id.listParticipantes);

        JogadorDAO jogadorDAO = new JogadorDAO(this);

        ArrayList<Jogador> jogadores = jogadorDAO.listarJogador();

        ArrayList<String> nomes = new ArrayList<>();
        for (Jogador j : jogadores) {
            nomes.add(j.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nomes
        );
        listParticipantes.setAdapter(adapter);

        btnStartGame = findViewById(R.id.btn_startGame);

        btnStartGame.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, JogoActivity.class);
            startActivity(intent);
            finish();
        });

    }
}