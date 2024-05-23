package org.johnny.controllers;

import org.johnny.models.Dependente;
import org.johnny.models.Mesa;
import org.johnny.models.Pedido;
import org.johnny.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping("/pedido")
    public String newPedido(@RequestBody Pedido newPedido){
        pedidoRepository.insert(newPedido);
        return  "Pedido criado";
    }

    @GetMapping("/pedidos")
    public List<Pedido> getAllPedidos(){
        return pedidoRepository.findAll();
    }

    @DeleteMapping("/pedido/{id_pedido}")
    public String deletePedido(@PathVariable int id_pedido){
        pedidoRepository.delete(id_pedido);
        return "Pedio deletado";
    }

    @GetMapping("/pedido/{id_pedido}")
    public Pedido getPedido(@PathVariable int id_pedido){
        return pedidoRepository.findPedido(id_pedido);
    }
//    Não funciona o editar pedido
    @PutMapping("/pedido/{id_pedido}")
    public String editPedido(@PathVariable int id_pedido, @RequestBody Pedido newPedido){
        pedidoRepository.update(id_pedido, newPedido);
        return "Pedio " + id_pedido + " editado";
    }

}
