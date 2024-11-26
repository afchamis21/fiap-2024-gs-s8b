package com.fiap.chamis.application.domain;

import java.time.LocalDate;

public class DadoEnergia {
    private Long id;
    private Long idProjetoEnergia;
    private Double energiaGerada;
    private LocalDate data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProjetoEnergia() {
        return idProjetoEnergia;
    }

    public void setIdProjetoEnergia(Long idProjetoEnergia) {
        this.idProjetoEnergia = idProjetoEnergia;
    }

    public Double getEnergiaGerada() {
        return energiaGerada;
    }

    public void setEnergiaGerada(Double energiaGerada) {
        this.energiaGerada = energiaGerada;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DadoEnergia [id=" + id + ", idProjetoEnergia=" + idProjetoEnergia + ", energiaGerada=" + energiaGerada
                + ", data=" + data + "]";
    }
}
