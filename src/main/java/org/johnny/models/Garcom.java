package org.johnny.models;

public class Garcom {
    private String fk_funcionario_cpf;
    private String fk_gerente_cpf;
    private int pontos;

    public Garcom(String fk_funcionario_cpf, String fk_gerente_cpf, int pontos) {
        this.fk_funcionario_cpf = fk_funcionario_cpf;
        this.fk_gerente_cpf = fk_gerente_cpf;
        this.pontos = pontos;
    }

    public Garcom() {

    }

    public String getFk_funcionario_cpf() {
        return fk_funcionario_cpf;
    }

    public void setFk_funcionario_cpf(String fk_funcionario_cpf) {
        this.fk_funcionario_cpf = fk_funcionario_cpf;
    }

    public String getFk_gerente_cpf() {
        return fk_gerente_cpf;
    }

    public void setFk_gerente_cpf(String fk_gerente_cpf) {
        this.fk_gerente_cpf = fk_gerente_cpf;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
