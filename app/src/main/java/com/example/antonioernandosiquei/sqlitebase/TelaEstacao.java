package com.example.antonioernandosiquei.sqlitebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaEstacao extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnServicos,btnMapa,btnComodidades;


    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_estacao);

        textView1 = (TextView)findViewById(R.id.txtEstacao);

        Estacao estacao = db.buscarEstacao(Integer.parseInt(getIntent().getStringExtra("codigo")));

        btnServicos = (Button)findViewById(R.id.btnServicos);
        btnMapa = (Button)findViewById(R.id.btnMapa);
        btnComodidades = (Button)findViewById(R.id.btnServicos);

        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TelaServicos.class);
                startActivity(intent);
            }
        });

        btnComodidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TelaServicos.class);
                startActivity(intent);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TelaMapa.class);
                startActivity(intent);
            }
        });

        textView1.setText(estacao.nome);
    }

}
