package com.fiap.chamis.application.core.menu;

public enum MenuLevel {
    ADMIN("Administração"),
    BASE("Base"),
    HOME("Home"),
    USER("Usuário"),
    ENERGY_DATA("Dados de Energia"),
    RENEWABLE_SOURCE("Fonte Renovável"),
    INVESTIMENT("Investimento"),
    ENERGY_PROJECT("Projeto de Energia"),
    ;
    private final String name;

    public String getLabel() {
        return "Selecione uma ação:";
    }

    public String getName() {
        return name;
    }

    private MenuLevel(String name) {
        this.name = name;
    }
}
