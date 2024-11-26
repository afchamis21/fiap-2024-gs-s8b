package com.fiap.chamis.application.core;

import java.util.Optional;

import com.fiap.chamis.application.core.menu.MenuLevel;
import com.fiap.chamis.application.domain.Usuario;

public class ServiceContext {
    private static ServiceContext instance = null;

    private MenuLevel menuLevel = MenuLevel.HOME;
    private Usuario usuario = null;

    public static ServiceContext getInstance() {
        if (instance == null) {
            instance = new ServiceContext();
        }

        return instance;
    }
    
    private ServiceContext(){
    }

    public MenuLevel getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(MenuLevel menuLevel) {
        this.menuLevel = menuLevel;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Optional<Usuario> getUsuario() {
        return Optional.ofNullable(usuario);
    }

    public boolean isLoggedIn() {
        return usuario != null;
    }
}
