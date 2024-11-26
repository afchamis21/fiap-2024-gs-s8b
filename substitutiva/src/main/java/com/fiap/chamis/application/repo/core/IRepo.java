package com.fiap.chamis.application.repo.core;

import java.util.List;
import java.util.Optional;

public interface IRepo <T, K> {
    T salvar(T object);
    Optional<T> buscarPorId(K id);
    void deletar(K id);
    List<T> buscarTodos();    
}
