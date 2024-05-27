package org.johnny.controllers;

import org.johnny.models.Garcom;
import org.johnny.repository.GarcomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class GarcomController {

    @Autowired
    private GarcomRepository garcomRepository;

    @GetMapping("/garcons")
    public List<Garcom> getAllGarcom(){
        return garcomRepository.findAll();
    }

    @GetMapping("/garcom/{fk_funcionario_cpf}")
    public Garcom getGarcom(@PathVariable String fk_funcionario_cpf){
        return garcomRepository.findGarcom(fk_funcionario_cpf);
    }

    @PostMapping("/garcom")
    public String newGarcom(@RequestBody Garcom newGarcom){
        garcomRepository.insert(newGarcom);
        return "Garcom cadastrado";
    }

    @DeleteMapping("/garcom/{fk_funcionario_cpf}")
    public String deleteGarcom(@PathVariable String fk_funcionario_cpf){
        garcomRepository.delete(fk_funcionario_cpf);
        return "Garcom " + fk_funcionario_cpf + " removido";
    }

    @PutMapping("/garcom/{fk_funcionario_cpf}")
    public String editFuncionario(@PathVariable String fk_funcionario_cpf, @RequestBody Garcom newGarcom){
        garcomRepository.update(fk_funcionario_cpf, newGarcom);
        return "Garcom " + fk_funcionario_cpf + " editado";
    }

    @PutMapping("/garcoms/updateGerente/{cpf}")
    public String updateGarconsGerente(@PathVariable String cpf) {
        garcomRepository.updateGerente(cpf);
        return "Garçons atualizados com o novo gerente " + cpf;
    }

    @PutMapping("/garcoms/updateGerente")
    public String updateGarconsGerente(@RequestBody Garcom newGerente) {
        List<Garcom> garcons = garcomRepository.findAll();
        for (Garcom garcom : garcons) {
            garcom.setFk_gerente_cpf(newGerente.getFk_funcionario_cpf());
            garcomRepository.update(garcom.getFk_funcionario_cpf(), garcom);
        }
        newGerente.setFk_gerente_cpf(null);
        garcomRepository.update(newGerente.getFk_funcionario_cpf(), newGerente);
        return "Garçons atualizados com o novo gerente " + newGerente.getFk_funcionario_cpf();
    }

    @GetMapping("/garcom/gerente")
    public Garcom getGerente() {
        return garcomRepository.findGerente();
    }




}
