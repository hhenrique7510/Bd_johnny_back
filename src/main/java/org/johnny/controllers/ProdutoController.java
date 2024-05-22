package com.bd.crud.controller;


import com.bd.crud.exception.ProdutoNotFoundException;
import com.bd.crud.model.Produto;
import com.bd.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Correção para 'List'
import java.lang.Exception; // Correção para 'Exception', geralmente não necessário explicitamente


@RestController
@CrossOrigin("http://localhost:3000")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/product")
    public Produto newProduct(@RequestBody Produto newProduto) {
        return produtoRepository.newProduto(newProduto);
    }

    @GetMapping("/products")
    public List<Produto> getAllProdutos() {
        return produtoRepository.getAllProdutos();
    }

    @GetMapping("/product/{id_prod}")
    public Produto getProdutoById(@PathVariable int id_prod) {
        try {
            return produtoRepository.getProdutoById(id_prod);
        } catch (Exception e) {
            throw new ProdutoNotFoundException(id_prod);
        }
    }

    @PutMapping("/product/{id_prod}")
    public Produto updateProduto(@RequestBody Produto newProduto, @PathVariable int id_prod) {
        try {
            return produtoRepository.updateProduto(newProduto, id_prod);
        } catch (Exception e) {
            throw new ProdutoNotFoundException(id_prod);
        }
    }

    @DeleteMapping("/product/{id_prod}")
    public String deleteProduct(@PathVariable int id_prod){
        try {
            produtoRepository.deleteProduto(id_prod);
            return "Product with id " + id_prod + " has been deleted successfully.";
        } catch (Exception e) {
            throw new ProdutoNotFoundException(id_prod);
        }
    }
}