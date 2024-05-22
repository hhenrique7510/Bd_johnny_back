package org.johnny.excepitons;

public class DependenteNotFoundException extends RuntimeException {
    public DependenteNotFoundException(String cpf_dependete){
        super("Funcionário não encontrado com CPF: " + cpf_dependete);
    }
}
