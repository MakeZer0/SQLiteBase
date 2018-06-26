package com.example.antonioernandosiquei.sqlitebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaEstacao extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_estacao);

        textView1 = (TextView)findViewById(R.id.txtEstacao);

        Estacao estacao = db.buscarEstacao(Integer.parseInt(getIntent().getStringExtra("codigo")));

        textView1.setText(estacao.nome);
    }

}
