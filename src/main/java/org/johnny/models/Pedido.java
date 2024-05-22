package org.johnny.models;

import java.time.LocalDateTime;

public class Pedido {
    private int id_pedido;
    private LocalDateTime data_hora;

    public Pedido(int id_pedido, LocalDateTime data_hora) {
        this.id_pedido = id_pedido;
        this.data_hora = data_hora;
    }

    public Pedido() {

    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }
}

