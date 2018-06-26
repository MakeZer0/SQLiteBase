package com.example.antonioernandosiquei.sqlitebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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

        listViewEstacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String conteudo = (String) listViewEstacoes.getItemAtPosition(i);

                //Toast.makeText(MainActivity.this,"Selecionado: " + conteudo, Toast.LENGTH_SHORT).show();
                String codigo = conteudo.substring(0,conteudo.indexOf("-"));

                Intent intent = new Intent(getApplicationContext(),TelaEstacao.class);
                intent.putExtra("codigo",codigo);
                startActivity(intent);

               // Cliente cliente = db.selecionarCliente(Integer.parseInt(codigo));

            }
        });


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
