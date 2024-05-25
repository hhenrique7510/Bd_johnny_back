package org.johnny.controllers;

import org.johnny.models.PedidoGarcomMesa;
import org.johnny.repository.PedidoGarcomMesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PedidoGarcomMesaController {

    @Autowired
    private PedidoGarcomMesaRepository pedidoGarcomMesaRepository;

    @PostMapping("/pedido_garcom_mesa")
    public String newPedidoGarcomMesa(@RequestBody PedidoGarcomMesa newPedidoGarcomMesa){
        pedidoGarcomMesaRepository.insert(newPedidoGarcomMesa);
        return "pedido garcom mesa criado";
    }

    @PutMapping("/pedido_garcom_mesa/{fk_funcionario_cpf}/{fk_mesa_id_mesa}/{fk_pedido_id_pedido}")
    public String editPedidoGarcomMesa(@PathVariable String fk_funcionario_cpf, @PathVariable int fk_mesa_id_mesa, @PathVariable int fk_pedido_id_pedido, @RequestBody PedidoGarcomMesa newPedidoGarcomMesa){
        pedidoGarcomMesaRepository.update(fk_funcionario_cpf, fk_mesa_id_mesa, fk_pedido_id_pedido, newPedidoGarcomMesa);
        return "Pedido Garcom Mesa atualizado";
    }

    @DeleteMapping ("/pedido_garcom_mesa/{fk_funcionario_cpf}/{fk_mesa_id_mesa}/{fk_pedido_id_pedido}")
    public String deletePedidoGarcomMesa(@PathVariable String fk_funcionario_cpf, @PathVariable int fk_mesa_id_mesa, @PathVariable int fk_pedido_id_pedido){
        pedidoGarcomMesaRepository.delete(fk_funcionario_cpf, fk_mesa_id_mesa, fk_pedido_id_pedido);
        return "Garcom " + fk_funcionario_cpf + "mesa" + fk_mesa_id_mesa + "pedido" + fk_pedido_id_pedido + "apagado da tabela";
    }

    @GetMapping("/pedido_garcom_mesas")//busca
    public List<PedidoGarcomMesa> getAllPedidoGarcom(){
        return pedidoGarcomMesaRepository.findAll();
    }

    @GetMapping ("/pedido_garcom_mesa/{fk_funcionario_cpf}/{fk_mesa_id_mesa}/{fk_pedido_id_pedido}")
    public PedidoGarcomMesa getPedidoGarcomMesa(@PathVariable String fk_funcionario_cpf, @PathVariable int fk_mesa_id_mesa, @PathVariable int fk_pedido_id_pedido){
        return pedidoGarcomMesaRepository.findPedidoGarcomMesa(fk_funcionario_cpf, fk_mesa_id_mesa, fk_pedido_id_pedido);
    }

    @GetMapping("/pedido_garcom_mesa/mesa/{fk_mesa_id_mesa}/abertos")
    public List<PedidoGarcomMesa> getPedidosAbertosByMesa(@PathVariable int fk_mesa_id_mesa) {
        return pedidoGarcomMesaRepository.findPedidosAbertosByMesa(fk_mesa_id_mesa);
    }

    @PutMapping("/pedido_garcom_mesa/fechar/{fk_mesa_id_mesa}")
    public String fecharPedidos(@PathVariable int fk_mesa_id_mesa) {
        pedidoGarcomMesaRepository.fecharPedidos(fk_mesa_id_mesa);
        return "Pedidos fechados para a mesa " + fk_mesa_id_mesa;
    }
}
