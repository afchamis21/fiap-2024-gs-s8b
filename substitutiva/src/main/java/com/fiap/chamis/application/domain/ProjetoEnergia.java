package com.fiap.chamis.application.domain;

public class ProjetoEnergia {
    private Long id;
    private String nome;
    private String localizacao;
    private Double capacidade;
    private Long idFonteRenovavel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Long getIdFonteRenovavel() {
        return idFonteRenovavel;
    }

    public void setIdFonteRenovavel(Long idFonteRenovavel) {
        this.idFonteRenovavel = idFonteRenovavel;
    }

    @Override
    public String toString() {
        return "ProjetoEnergia [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + ", capacidade="
                + capacidade + ", idFonteRenovavel=" + idFonteRenovavel + "]";
    }
}
