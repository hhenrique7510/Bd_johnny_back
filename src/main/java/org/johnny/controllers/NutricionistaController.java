package org.johnny.controllers;

import org.johnny.models.Nutricionista;
import org.johnny.repository.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class NutricionistaController {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @PostMapping("/nutricionista")
    Nutricionista newNutricionista(@RequestBody Nutricionista newNutricionista) {
        return nutricionistaRepository.saveNutricionista(newNutricionista);
    }

    @GetMapping("/nutricionistas")
    List<Nutricionista> getAllNutricionistas() {
        return nutricionistaRepository.listNutricionista();
    }

    @GetMapping("/nutricionista/{cpf}")
    Nutricionista getNutricionistaByCpf(@PathVariable String cpf) {
        return nutricionistaRepository.findByCpfNutricionista(cpf)
                .orElseThrow(() -> new RuntimeException("Nutricionista com CPF " + cpf + " não encontrado."));
    }

    @PutMapping("/nutricionistaUpdate/{cpf}")
    public ResponseEntity<?> updateNutricionista(@RequestBody Nutricionista updatedNutricionista, @PathVariable String cpf) {
        return nutricionistaRepository.findByCpfNutricionista(cpf)
                .map(existingNutricionista -> {
                    Nutricionista updated = nutricionistaRepository.updateNutricionista(cpf, updatedNutricionista);
                    if (updated == null) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao atualizar o nutricionista.");
                    }
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nutricionista com CPF " + cpf + " não encontrado para atualização."));
    }

    @DeleteMapping("/nutricionistaDelete/{cpf}")
    public ResponseEntity<String> deleteNutricionista(@PathVariable String cpf) {
        boolean deleted = nutricionistaRepository.deleteNutricionistaByCpf(cpf);
        if (deleted) {
            return ResponseEntity.ok("Nutricionista com CPF " + cpf + " deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nutricionista com CPF " + cpf + " não encontrado para exclusão.");
        }
    }
}

