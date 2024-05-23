package org.johnny.models;

import org.springframework.stereotype.Component;

@Component
public class PedidoGarcomMesa {
    private String fk_funcionario_cpf;
    private int fk_mesa_id_mesa;
    private int fk_pedido_id_pedido;
    private String status;

    public PedidoGarcomMesa(String fk_funcionario_cpf, int fk_mesa_id_mesa, int fk_pedido_id_pedido, String status) {
        this.fk_funcionario_cpf = fk_funcionario_cpf;
        this.fk_mesa_id_mesa = fk_mesa_id_mesa;
        this.fk_pedido_id_pedido = fk_pedido_id_pedido;
        this.status = status;
    }

    public PedidoGarcomMesa() {

    }

    public String getFk_funcionario_cpf() {
        return fk_funcionario_cpf;
    }

    public void setFk_funcionario_cpf(String fk_funcionario_cpf) {
        this.fk_funcionario_cpf = fk_funcionario_cpf;
    }

    public int getFk_mesa_id_mesa() {
        return fk_mesa_id_mesa;
    }

    public void setFk_mesa_id_mesa(int fk_mesa_id_mesa) {
        this.fk_mesa_id_mesa = fk_mesa_id_mesa;
    }

    public int getFk_pedido_id_pedido() {
        return fk_pedido_id_pedido;
    }

    public void setFk_pedido_id_pedido(int fk_pedido_id_pedido) {
        this.fk_pedido_id_pedido = fk_pedido_id_pedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
