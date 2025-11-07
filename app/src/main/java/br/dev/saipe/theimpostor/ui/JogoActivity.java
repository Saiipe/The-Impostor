package br.dev.saipe.theimpostor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.dev.saipe.theimpostor.R;

public class JogoActivity extends AppCompatActivity {

    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);


        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(v -> {
            Intent intent = new Intent(JogoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}