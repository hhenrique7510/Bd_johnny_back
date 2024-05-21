package org.johnny.controllers;

import org.johnny.excepitons.FuncionarioNotFoundException;
import org.johnny.models.Funcionario;
import org.johnny.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/funcionario")
    Funcionario newFuncionario (@RequestBody Funcionario newFuncionario){
        return funcionarioRepository.saveFuncionario(newFuncionario);
    }

    @GetMapping("/funcionarios")
    List<Funcionario> getAllFuncionarios(){
        return funcionarioRepository.listFuncionario();
    }

    @GetMapping("/funcionario/{cpf}")
    Funcionario getFuncionarioByCpf(@PathVariable String cpf) {
        return funcionarioRepository.findByCpfFuncionario(cpf)
                .orElseThrow(() -> new FuncionarioNotFoundException(cpf));
    }


    @PutMapping("/funcionarioUpdate/{cpf}")
    public ResponseEntity<?> updateFuncionario(@RequestBody Funcionario updatedFuncionario, @PathVariable String cpf) {
        return funcionarioRepository.findByCpfFuncionario(cpf)
                .map(existingFuncionario -> {
                    // Supõe que a atualização sempre será bem-sucedida
                    Funcionario updated = funcionarioRepository.updateFuncionario(cpf, updatedFuncionario);
                    if (updated == null) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao atualizar o funcionário.");
                    }
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com CPF " + cpf + " não encontrado para atualização.\n"));
    }



    @DeleteMapping("/funcionarioDelete/{cpf}")
    public ResponseEntity<String> deleteFuncionario(@PathVariable String cpf) {
        boolean deleted = funcionarioRepository.deleteFuncionarioByCpf(cpf);
        if (deleted) {
            return ResponseEntity.ok("Funcionário com CPF " + cpf + " deletado com sucesso!\n");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com CPF " + cpf + " não encontrado para exclusão.\n");
        }
    }

}
