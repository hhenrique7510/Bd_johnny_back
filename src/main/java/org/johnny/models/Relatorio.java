package org.johnny.models;

import java.sql.Timestamp;

public class Relatorio {
    private int pedidoId;
    private Timestamp dataHora;
    private int mesa;
    private float valorTotal;
    private String garcom;

    // Getters and Setters
    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getGarcom() {
        return garcom;
    }

    public void setGarcom(String garcom) {
        this.garcom = garcom;
    }
}
