package com.example.antonioernandosiquei.sqlitebase;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaServicos extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    ListView listViewServicos;
    InputMethodManager imm;


    ImageButton btnLanchonetes, btnLojas, btnMaquinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servicos);
        TextView textView1;

        listViewServicos = (ListView)findViewById(R.id.lstServicos);
        textView1 = (TextView)findViewById(R.id.txtEstacao2);

        btnLanchonetes = (ImageButton)findViewById(R.id.btnLanchonetes);
        btnLojas = (ImageButton)findViewById(R.id.btnLojas);
        btnMaquinas = (ImageButton)findViewById(R.id.btnMaquinas);

        btnLanchonetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarServicos(1);
            }
        });

        btnLojas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarServicos(2);
            }
        });

        btnMaquinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarServicos(3);
            }
        });

       // Estacao estacao = db.buscarEstacao(Integer.parseInt(getIntent().getStringExtra("nome")));

        textView1.setText(String.valueOf(getIntent().getStringExtra("nome")));

        //ADICIONAR LANCHONETE
        /*Servico servico = new Servico();
        servico.setNome("Mc Donalds");
        servico.setTipo(1);
        servico.setLocalização("Próximo a Saida Senador Vergueiro"); */

        //ADICIONAR LOJA
        /*Servico servico2 = new Servico();
        servico2.setNome("Joalheira");
        servico2.setTipo(2);
        servico2.setLocalização("Próximo a Saida São Luiz");

        //ADICIONAR MAQUINA
        Servico servico3 = new Servico();
        servico3.setNome("Máquina de Bebidas");
        servico3.setTipo(3);
        servico3.setLocalização("Próximo a Saida Faria Lima");


        db.addServico(servico2);
        db.addServico(servico3); */

    }

    public void listarServicos(int tipo){

        List<Servico> servicos = db.listaServicos(tipo);

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(TelaServicos.this, android.R.layout.simple_list_item_1, arrayList);

        listViewServicos.setAdapter(adapter);

        for(Servico c : servicos) {
            //Log.d("Lista","\nID: " + c.getCodigo() + "Nome: " + c.getNome());
            if (c.getLocalização() == null){
                arrayList.add(c.getNome());
            }else{
                arrayList.add(c.getNome() + " - " + c.getLocalização());
            }
            adapter.notifyDataSetChanged();
        }
    }
}
