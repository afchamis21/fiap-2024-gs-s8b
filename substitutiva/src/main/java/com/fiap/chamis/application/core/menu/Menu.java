package com.fiap.chamis.application.core.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.core.ServiceContext;
import com.fiap.chamis.util.InputUtils;

public class Menu {
    public void run() {
        while (true) {
            MenuLevel menuLevel = ServiceContext.getInstance().getMenuLevel();

            System.out.println();
            this.exibirOpcoes(menuLevel);
            MenuOption option = this.escolherOpcao(menuLevel);
            
            System.out.println();
            option.getAction().execute();
        }
    }

    private void exibirOpcoes(MenuLevel menuLevel) {
        List<MenuOption> generalOptions = MenuOption.getAllByLevel(MenuLevel.BASE);
        List<MenuOption> options = MenuOption.getAllByLevel(menuLevel);
        List<MenuOption> finalOptions = new ArrayList<>();
        finalOptions.addAll(generalOptions);
        finalOptions.addAll(options);

        System.out.println("Menu: " + menuLevel.getName());
        finalOptions.forEach(option -> {
            System.out.println(option.getValue() + " - " + option.getLabel());
        });
    }

    private MenuOption escolherOpcao(MenuLevel menuLevel) {
        Integer value = InputUtils.getIntegerInput(menuLevel.getLabel());
        Optional<MenuOption> option = MenuOption.getByLevelAndValue(menuLevel, value);
        if (option.isEmpty()) {
            option = MenuOption.getByLevelAndValue(MenuLevel.BASE, value);
        }

        if (option.isEmpty()) {
            System.out.println("Escolha uma opção válida");
            return escolherOpcao(menuLevel);
        }

        return option.get();
    }
}
