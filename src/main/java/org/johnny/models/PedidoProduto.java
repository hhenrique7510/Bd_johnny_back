package org.johnny.models;

public class PedidoProduto {
    private int fk_produtos_id_prod;
    private int fk_pedido_id_pedido;
    private int qtd_produto;

    public PedidoProduto(int fk_produtos_id_prod, int fk_pedido_id_pedido, int qtd_produto) {
        this.fk_produtos_id_prod = fk_produtos_id_prod;
        this.fk_pedido_id_pedido = fk_pedido_id_pedido;
        this.qtd_produto = qtd_produto;
    }

    public PedidoProduto() {

    }

    public int getFk_produtos_id_prod() {
        return fk_produtos_id_prod;
    }

    public void setFk_produtos_id_prod(int fk_produtos_id_prod) {
        this.fk_produtos_id_prod = fk_produtos_id_prod;
    }

    public int getFk_pedido_id_pedido() {
        return fk_pedido_id_pedido;
    }

    public void setFk_pedido_id_pedido(int fk_pedido_id_pedido) {
        this.fk_pedido_id_pedido = fk_pedido_id_pedido;
    }

    public int getQtd_produto() {
        return qtd_produto;
    }

    public void setQtd_produto(int qtd_produto) {
        this.qtd_produto = qtd_produto;
    }
}
