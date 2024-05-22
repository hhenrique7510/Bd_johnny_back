package org.johnny.models;

import java.util.Date;

public class Dependente {
    private String nome;
    private String cpf_dependente;
    private String fk_funcionario_cpf;

    public Dependente( String nome, String cpf_dependente,String fk_funcionario_cpf) {
        this.nome = nome;
        this.cpf_dependente = cpf_dependente;
        this.fk_funcionario_cpf = fk_funcionario_cpf;
    }

    public String getFk_funcionario_cpf() {
        return fk_funcionario_cpf;
    }

    public void setFk_funcionario_cpf(String fk_funcionario_cpf) {
        this.fk_funcionario_cpf = fk_funcionario_cpf;
    }

    public String getCpf_dependente() {
        return cpf_dependente;
    }

    public void setCpf_dependente(String cpf_dependente) {
        this.cpf_dependente = cpf_dependente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}