package org.johnny.controllers;

import org.johnny.models.Dependente;
import org.johnny.repository.DependenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DependenteController {

    @Autowired
    private DependenteRepository dependenteRepository;

    @PostMapping("/dependente")
    Dependente newDependente(@RequestBody Dependente newDependente) {
        return dependenteRepository.saveDependente(newDependente);
    }

    @GetMapping("/dependentes")
    List<Dependente> getAllDependentes() {
        return dependenteRepository.listDependente();
    }

    @GetMapping("/dependenteByFuncionario/{fk_funcionario_cpf}")
    public ResponseEntity<List<Dependente>> getDependentesByFuncionarioCpf(@PathVariable String fk_funcionario_cpf) {
        List<Dependente> dependentes = dependenteRepository.findByCpfFuncionario(fk_funcionario_cpf);
        if (dependentes.isEmpty()) {
            return ResponseEntity.noContent().build(); // ou ResponseEntity.notFound().build() se preferir tratar lista vazia como erro
        }
        return ResponseEntity.ok(dependentes);
    }

    @DeleteMapping("/dependenteDelete/{cpf_dependente}")
    public ResponseEntity<String> deleteDependente(@PathVariable String cpf_dependente) {
        boolean deleted = dependenteRepository.deleteDependeteByCpf(cpf_dependente);
        if (deleted) {
            return ResponseEntity.ok("Dependente com CPF " + cpf_dependente + " deletado com sucesso!\n");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com CPF " + cpf_dependente + " não encontrado para exclusão.\n");
        }
    }


    @PutMapping("dependenteUpdate/{cpf_dependente}")
    public String editDependente(@PathVariable String cpf_dependente, @RequestBody Dependente newDependente){
        dependenteRepository.updateDependente(cpf_dependente, newDependente);
        return "Dependente editado";
    }
}

