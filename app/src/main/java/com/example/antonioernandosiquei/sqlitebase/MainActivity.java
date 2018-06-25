package com.example.antonioernandosiquei.sqlitebase;

import android.app.Service;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editCodigo, editNome, editTelefone, editEmail, editSenha;
    Button btnLimpar, btnSalvar, btnExcluir;
    ListView listViewClientes;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    InputMethodManager imm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCodigo = (EditText)findViewById(R.id.editCodigo);
        editNome = (EditText)findViewById(R.id.editNome);
        editSenha = (EditText)findViewById(R.id.editSenha);
        editTelefone = (EditText)findViewById(R.id.editTelefone);
        editEmail = (EditText)findViewById(R.id.editEmail);

        btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnExcluir = (Button)findViewById(R.id.btnExcluir);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);

        listViewClientes = (ListView)findViewById(R.id.listViewClientes);

        imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String conteudo = (String) listViewClientes.getItemAtPosition(i);

                //Toast.makeText(MainActivity.this,"Selecionado: " + conteudo, Toast.LENGTH_SHORT).show();
                String codigo = conteudo.substring(0,conteudo.indexOf("-"));

                Cliente cliente = db.selecionarCliente(Integer.parseInt(codigo));

                editCodigo.setText(String.valueOf(cliente.getCodigo()));
                editNome.setText(cliente.getNome());
                editSenha.setText(cliente.getSenha());
                editTelefone.setText(cliente.getTelefone());
                editEmail.setText(cliente.getEmail());
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = editCodigo.getText().toString();
                String nome = editNome.getText().toString();
                String senha = editSenha.getText().toString();
                String telefone = editTelefone.getText().toString();
                String email = editEmail.getText().toString();


                if(nome.isEmpty()) {
                    editNome.setError("Este campo é obrigatório");

                }else if(senha.isEmpty()){
                    editSenha.setError("Este campo é obrigatório");
                }else{
                    if(codigo.isEmpty()){
                        db.addCliente(new Cliente(nome, senha, telefone, email));
                        Toast.makeText(MainActivity.this,"Salvo com sucecssos", Toast.LENGTH_LONG).show();
                        limpaCampos();
                        listarClientes();
                        escondeTeclado();
                    }else{
                        db.atualizaCliente(new Cliente(Integer.parseInt(codigo),nome,senha,telefone,email));

                        Toast.makeText(MainActivity.this,"Atualizado com sucecsso", Toast.LENGTH_LONG).show();
                        limpaCampos();
                        listarClientes();
                        escondeTeclado();
                    }
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = editCodigo.getText().toString();

                if (codigo.isEmpty()){
                    Toast.makeText(MainActivity.this,"Nenhum cliente selecionado", Toast.LENGTH_LONG).show();
                }else{
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(Integer.parseInt(codigo));
                    db.apagarCliente(cliente);

                    Toast.makeText(MainActivity.this,"Apagado com sucecsso", Toast.LENGTH_LONG).show();
                    limpaCampos();
                    listarClientes();
                    escondeTeclado();
                }
            }
        });
        //db.addCliente(new Cliente("Antonio Ernando","954633904", "nandinho@hotmail.com"));

        listarClientes();

        //Toast.makeText(MainActivity.this,"Salvo com sucecsso", Toast.LENGTH_LONG).show();



        /*Cliente cliente1 = new Cliente();
        cliente1.setCodigo(1);
        cliente1.setNome("Antonio Siqueira");
        cliente1.setTelefone("954633904");
        cliente1.setEmail("nandao@antonio.com");

        db.atualizaCliente(cliente1);

        Cliente cliente = db.selecionarCliente(1);
        Log.d("Cliente selecionado", "Codigo: " + cliente.getCodigo() + " Nome: " + cliente.getNome() + " Telefone: " + cliente.getTelefone() + " Email:" + cliente.getEmail());
        */

    }
    public void listarClientes(){

        List<Cliente> clientes = db.listaTodosClientes();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewClientes.setAdapter(adapter);



        for(Cliente c : clientes) {
            //Log.d("Lista","\nID: " + c.getCodigo() + "Nome: " + c.getNome());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }

    void limpaCampos(){
        editCodigo.setText("");
        editNome.setText("");
        editTelefone.setText("");
        editEmail.setText("");
        editSenha.setText("");

        editNome.requestFocus();
    }

    void escondeTeclado(){
        imm.hideSoftInputFromWindow(editNome.getWindowToken(),0);
    }
}
