package org.johnny.controllers;

import org.johnny.models.Relatorio;
import org.johnny.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class RelatorioController {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @GetMapping("/relatorios")
    public List<Relatorio> getRelatorios() {
        return relatorioRepository.findRelatorios();
    }
}
