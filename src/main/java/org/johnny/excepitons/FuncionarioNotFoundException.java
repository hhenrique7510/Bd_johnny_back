package org.johnny.excepitons;

public class FuncionarioNotFoundException extends RuntimeException {
    public FuncionarioNotFoundException(String cpf){
        super("Funcionário não encontrado com CPF: " + cpf);
    }
}
