package br.dev.saipe.theimpostor.ui;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
    private Button btnAddPerson;

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

// Lista de objetos Jogador
        ArrayList<Jogador> jogadores = jogadorDAO.listarJogador();
        ArrayList<String> nomesJogador = new ArrayList<>();
        for (Jogador j : jogadores) {
            nomesJogador.add(j.getNome());
        }

// Adapter
        ArrayAdapter<String> adapterJogador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nomesJogador
        );
        listParticipantes.setAdapter(adapterJogador);

// Adicionar jogador
        btnAddPerson = findViewById(R.id.btn_addPeople);
        btnAddPerson.setOnClickListener(v -> {
            final EditText input = new EditText(MainActivity.this);
            input.setHint("DIGITE O NOME DO JOGADOR");

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Adicionar Jogador")
                    .setView(input)
                    .setPositiveButton("OK", (dialog, which) -> {
                        String texto = input.getText().toString().trim();

                        if (!texto.isEmpty()) {
                            // Inserir no DAO
                            jogadorDAO.inserirJogador(new Jogador(texto));

                            // Adicionar na lista local de objetos
                            Jogador novoJogador = new Jogador(texto);
                            jogadores.add(novoJogador);

                            // Adicionar no adapter e atualizar a lista visual
                            nomesJogador.add(texto);
                            adapterJogador.notifyDataSetChanged();

                            Toast.makeText(MainActivity.this, "Jogador adicionado: " + texto, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Digite um nome válido!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                    .show();
        });

// Remover jogador com duplo clique
        listParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private long lastClickTime = 0;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastClickTime < 300) { // duplo clique
                    Jogador jogador = jogadores.get(position); // pega da lista de objetos
                    int jogadorId = jogador.getId();

                    // remove do DB
                    jogadorDAO.removerJogador(jogadorId);

                    // remove da lista local e do adapter
                    jogadores.remove(position);
                    nomesJogador.remove(position);
                    adapterJogador.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, "Jogador removido: " + jogador.getNome(), Toast.LENGTH_SHORT).show();
                }

                lastClickTime = currentTime;
            }
        });



        //=================TEMAS=================
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