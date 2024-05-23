package org.johnny.models;

public class Nutricionista {
    private String fk_funcionario_cpf;

    public Nutricionista() {
    }

    public Nutricionista(String fk_funcionario_cpf) {
        this.fk_funcionario_cpf = fk_funcionario_cpf;
    }

    public String getFkFuncionarioCpf() {
        return fk_funcionario_cpf;
    }

    public void setFkFuncionarioCpf(String fk_funcionario_cpf) {
        this.fk_funcionario_cpf = fk_funcionario_cpf;
    }
}

