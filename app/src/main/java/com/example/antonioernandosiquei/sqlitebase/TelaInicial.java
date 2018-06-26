package com.example.antonioernandosiquei.sqlitebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    ListView listViewEstacoes;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);



        listViewEstacoes = (ListView)findViewById(R.id.listViewEstacoes);

        //db.addEstacao(new Estacao("Jabaquara","São Paulo",123312312.12,123123123.32));
        /*Estacao estacao = new Estacao(5,"DNAS",12312312.,123123.21,"dsadas");
        db.apagarEstacao(estacao);*/
        listarEstacoes();


    }

    public void listarEstacoes(){

        List<Estacao> estacoes = db.listaTodasEstacoes();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(TelaInicial.this, android.R.layout.simple_list_item_1, arrayList);

        listViewEstacoes.setAdapter(adapter);

        for(Estacao c : estacoes) {
            Log.d("Lista","\nID: " + c.getCodigo() + "Nome: " + c.getNome());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }

}
