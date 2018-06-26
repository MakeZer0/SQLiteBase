package com.example.antonioernandosiquei.sqlitebase;

public class Estacao {

    int codigo;
    String nome;
    String cidade;
    Double latitude;
    Double longitude;

    public Estacao(int codigo, String nome, Double latitude, Double longitude, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidade = cidade;
    }

    public Estacao() {
    }

    public Estacao(String nome, String cidade, Double latitude, Double longitude) {
        this.nome = nome;
        this.cidade = cidade;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
