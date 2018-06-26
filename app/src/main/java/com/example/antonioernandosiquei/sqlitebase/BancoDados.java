package com.example.antonioernandosiquei.sqlitebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CLIENTE = "bd_usuarios";

    private static final String TABELA_CLIENTE = "tb_clientes";
    private static final String TABELA_ESTACAO = "tb_estacoes";

    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_NOME  = "nome";
    private static final String COLUNA_TELEFONE  = "telefone";
    private static final String COLUNA_EMAIL  = "email";
    private static final String COLUNA_SENHA = "senha";

    private static final String COLUNA_CIDADE = "cidade";
    private static final String COLUNA_LATITUDE = "latitude";
    private static final String COLUNA_LONGITUDE = "longitude";


    public BancoDados(Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_CLIENTE + " ("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, " + COLUNA_NOME + " TEXT, " + COLUNA_SENHA + " TEXT, "
                + COLUNA_TELEFONE + " TEXT, " + COLUNA_EMAIL + " TEXT)";
        db.execSQL(QUERY_COLUNA);

        String QUERY_COLUNA2 = "CREATE TABLE " + TABELA_ESTACAO + " ("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, " + COLUNA_NOME + " TEXT, " + COLUNA_CIDADE + " TEXT, "
                + COLUNA_LATITUDE + " REAL, " + COLUNA_LONGITUDE + " REAL)";
        db.execSQL(QUERY_COLUNA2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    void addEstacao(Estacao estacao){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, estacao.getNome());
        values.put(COLUNA_CIDADE, estacao.getCidade());
        values.put(COLUNA_LATITUDE, estacao.getLatitude());
        values.put(COLUNA_LONGITUDE, estacao.getLongitude());

        db.insert(TABELA_ESTACAO, null, values);
        db.close();
    }

    void apagarEstacao(Estacao estacao){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_ESTACAO,COLUNA_CODIGO + " = ?", new String[] { String.valueOf(estacao.getCodigo())});

        db.close();

    }


    public List<Estacao> listaTodasEstacoes(){
        List<Estacao> listaEstacoes = new ArrayList<Estacao>();

        String query = "SELECT * FROM " + TABELA_ESTACAO;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query,null);

        if(c.moveToFirst()){
            do{
                Estacao estacao =  new Estacao();
                estacao.setCodigo(Integer.parseInt(c.getString(0)));
                estacao.setNome(c.getString(1));
                estacao.setCidade(c.getString(2));
                estacao.setLatitude(Double.parseDouble(c.getString(3)));
                estacao.setLongitude(Double.parseDouble(c.getString(4)));

                listaEstacoes.add(estacao);

            } while (c.moveToNext());
        }

        return listaEstacoes;
    }

    void addCliente(Cliente cliente){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_SENHA, cliente.getSenha());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_EMAIL, cliente.getEmail());

        db.insert(TABELA_CLIENTE, null, values);
        db.close();
    }

    void apagarCliente(Cliente cliente){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CLIENTE,COLUNA_CODIGO + " = ?", new String[] { String.valueOf(cliente.getCodigo())});

        db.close();

    }

    Cliente selecionarCliente(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_CLIENTE, new String[] { COLUNA_CODIGO,
        COLUNA_NOME, COLUNA_SENHA, COLUNA_TELEFONE, COLUNA_EMAIL}, COLUNA_CODIGO + " = ?",
                new String[] { String.valueOf(codigo)},null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return cliente;

    }

    Cliente buscarCliente(String nome){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_CLIENTE, new String[] { COLUNA_CODIGO,
                        COLUNA_NOME, COLUNA_SENHA, COLUNA_TELEFONE, COLUNA_EMAIL}, COLUNA_NOME + " = ?",
                new String[] { String.valueOf(nome)},null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
        }else{
            return null;
        }

        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return cliente;

    }

    void atualizaCliente(Cliente cliente){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_SENHA, cliente.getSenha());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_EMAIL, cliente.getEmail());

        db.update(TABELA_CLIENTE,values,COLUNA_CODIGO + " = ?", new String[]{String.valueOf(cliente.getCodigo())});
    }

    public List<Cliente> listaTodosClientes(){
        List<Cliente> listaClientes = new ArrayList<Cliente>();

        String query = "SELECT * FROM " + TABELA_CLIENTE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query,null);

        if(c.moveToFirst()){
            do{
                Cliente cliente =  new Cliente();
                cliente.setCodigo(Integer.parseInt(c.getString(0)));
                cliente.setNome(c.getString(1));
                cliente.setSenha(c.getString(2));
                cliente.setTelefone(c.getString(3));
                cliente.setEmail(c.getString(4));

                listaClientes.add(cliente);

            } while (c.moveToNext());
        }

        return listaClientes;
    }

}
