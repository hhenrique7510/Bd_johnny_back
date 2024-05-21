package org.johnny.models;

public class Funcionario {
    private String cpf;
    private float salario;
    private String celular;
    private String nome;
    private String senha;
    private String emailPrincipal;
    private String emailSecundario;
    private String rua;
    private int numero;
    private int cep;
    private String bairro;

    public Funcionario(String cpf, float salario, String celular, String nome, String senha, String emailPrincipal, String emailSecundario, String rua, int numero, int cep, String bairro) {
        this.cpf = cpf;
        this.salario = salario;
        this.celular = celular;
        this.nome = nome;
        this.senha = senha;
        this.emailPrincipal = emailPrincipal;
        this.emailSecundario = emailSecundario;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(String emailSecundario) {
        this.emailSecundario = emailSecundario;
    }

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
