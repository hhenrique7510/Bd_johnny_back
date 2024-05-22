package com.bd.crud.model;

public class Produto {
    private int id_prod; // Nome da coluna no banco de dados
    private String nome;
    private float valor;
    private String produtos_tipo;

    // Getters e Setters
    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getProdutos_tipo() {
        return produtos_tipo;
    }

    public void setProdutos_tipo(String produtos_tipo) {
        this.produtos_tipo = produtos_tipo;
    }
}