package org.johnny.controllers;

import org.johnny.models.Mesa;
import org.johnny.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping("/mesas")
    public List<Mesa> getAllMesas(){
        return mesaRepository.findAll();
    }


}
