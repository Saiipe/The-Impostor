package br.dev.saipe.theimpostor.ui;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import br.dev.saipe.theimpostor.R;
import br.dev.saipe.theimpostor.dao.JogadorDAO;
import br.dev.saipe.theimpostor.dao.TemaDAO;
import br.dev.saipe.theimpostor.model.Jogador;
import br.dev.saipe.theimpostor.model.Tema;

public class MainActivity extends AppCompatActivity {

    private ListView listParticipantes;
    private ListView listarTemas;
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
        ArrayList<String> nomesJogador = new ArrayList<>();

        for (Jogador j : jogadores) {
            nomesJogador.add(j.getNome());
        }

        ArrayAdapter<String> adapterJogador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nomesJogador
        );
        listParticipantes.setAdapter(adapterJogador);

        listarTemas = findViewById(R.id.listTemas);

        TemaDAO temaDAO = new TemaDAO(this);

        ArrayList<Tema> temas = temaDAO.listarTemas();
        ArrayList<String> nomeTema = new ArrayList<>();

        for (Tema t : temas){
            nomeTema.add(t.getNome());
        }

        ArrayAdapter<String> adapterTema = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_activated_1,
                nomeTema
        );
        listarTemas.setAdapter(adapterTema);


        listarTemas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listarTemas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int ultimaSelecionada = ListView.INVALID_POSITION;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == ultimaSelecionada) {

                    listarTemas.setItemChecked(position, false);
                    ultimaSelecionada = ListView.INVALID_POSITION;
                    Toast.makeText(MainActivity.this, "Tema desmarcado", Toast.LENGTH_SHORT).show();
                } else {
                    listarTemas.setItemChecked(position, true);
                    ultimaSelecionada = position;

                    Tema temaSelecionado = temas.get(position);
                    Toast.makeText(MainActivity.this, "Tema selecionado: " + temaSelecionado.getNome(), Toast.LENGTH_SHORT).show();
                }
            }
    });

        btnStartGame = findViewById(R.id.btn_startGame);

        btnStartGame.setOnClickListener(v -> {
            int posicaoSelecionada = listarTemas.getCheckedItemPosition();

            if (posicaoSelecionada != ListView.INVALID_POSITION) {
                Tema temaSelecionado = temas.get(posicaoSelecionada);

                // cria o Intent e envia o tema como extra
                Intent intent = new Intent(MainActivity.this, JogoActivity.class);
                intent.putExtra("temaSelecionado", temaSelecionado.getNome()); // envia o nome do tema
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Selecione um tema antes de começar!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}