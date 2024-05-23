package org.johnny.controllers;

import org.johnny.models.Mesa;
import org.johnny.models.Pedido;
import org.johnny.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping("/mesas")
    public List<Mesa> getAllMesas(){
        return mesaRepository.findAll();
    }

    @PostMapping("/mesa")
    public  String newMesa(@RequestBody Mesa newMesa){
        mesaRepository.insert(newMesa);
        return "Mesa criado";
    }

    @DeleteMapping("/mesa/{id_mesa}")
    public String deleteMesa(@PathVariable int id_mesa){
        mesaRepository.delete(id_mesa);
        return "Mesa deletada";
    }

    @PutMapping("/mesa/{id_mesa}")
    public String editMesa(@PathVariable int id_mesa, @RequestBody Mesa newMesa){
        mesaRepository.update(id_mesa, newMesa);
        return "Pedio " + id_mesa + " editado";
    }
}
