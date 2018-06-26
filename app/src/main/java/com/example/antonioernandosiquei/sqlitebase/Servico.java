package com.example.antonioernandosiquei.sqlitebase;

public class Servico {

    int codigo;
    int tipo; //TIPO 1:LANCHONETE     TIPO 2:LOJAS      TIPO 3:MAQUINAS
    String nome;
    String localização;

    public Servico() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalização() {
        return localização;
    }

    public void setLocalização(String localização) {
        this.localização = localização;
    }
}
