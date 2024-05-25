package org.johnny.controllers;

import org.johnny.models.PedidoProduto;
import org.johnny.repository.PedidoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PedidoProdutoController {

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @PostMapping("/pedido_produto")
    public String newPedidoProduto(@RequestBody PedidoProduto newPedidoProduto){
        pedidoProdutoRepository.insert(newPedidoProduto);
        return "pedido produto criado";
    }

    @PutMapping("/pedido_produto/{fk_produtos_id_prod}/{fk_pedido_id_pedido}")
    public String editPedidoProduto(@PathVariable int fk_produtos_id_prod, @PathVariable int fk_pedido_id_pedido, @RequestBody PedidoProduto newPedidoProduto){
        pedidoProdutoRepository.update(fk_produtos_id_prod, fk_pedido_id_pedido, newPedidoProduto);
        return "Pedido produto atualizado";
    }

    @DeleteMapping ("/pedido_produto/{fk_produtos_id_prod}/{fk_pedido_id_pedido}")
    public String deletePedidoProduto(@PathVariable int fk_produtos_id_prod, @PathVariable int fk_pedido_id_pedido){
        pedidoProdutoRepository.delete(fk_produtos_id_prod, fk_pedido_id_pedido);
        return "produto " + fk_produtos_id_prod + "pedido" + fk_pedido_id_pedido + "apagado da tabela";
    }

    @GetMapping("/pedidos_produtos")//busca
    public List<PedidoProduto> getAllPedidoProduto(){
        return pedidoProdutoRepository.findAll();
    }

    @GetMapping ("/pedido_produto/{fk_produtos_id_prod}/{fk_pedido_id_pedido}")
    public PedidoProduto getPedidoProduto(@PathVariable int fk_produtos_id_prod, @PathVariable int fk_pedido_id_pedido){
        return pedidoProdutoRepository.findPedidoProduto(fk_produtos_id_prod, fk_pedido_id_pedido);
    }

    @PostMapping("/pedido_produto/pedidos")
    public List<PedidoProduto> getPedidoProdutosByPedidoIds(@RequestBody List<Integer> pedidoIds) {
        return pedidoProdutoRepository.findPedidoProdutosByPedidoIds(pedidoIds);
    }



}
