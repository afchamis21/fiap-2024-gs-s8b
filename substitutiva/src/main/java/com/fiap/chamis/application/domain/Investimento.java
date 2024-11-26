package com.fiap.chamis.application.domain;

import java.math.BigDecimal;

public class Investimento {
    private Long id;
    private Long idUsuario;
    private Long idProjetoEnergia;
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdProjetoEnergia() {
        return idProjetoEnergia;
    }

    public void setIdProjetoEnergia(Long idProjetoEnergia) {
        this.idProjetoEnergia = idProjetoEnergia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Investimento [id=" + id + ", idUsuario=" + idUsuario + ", idProjetoEnergia=" + idProjetoEnergia
                + ", valor=" + valor + "]";
    }
}
