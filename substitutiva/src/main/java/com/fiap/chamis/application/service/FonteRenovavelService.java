package com.fiap.chamis.application.service;


import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.FonteRenovavel;
import com.fiap.chamis.application.repo.FonteRenovavelRepository;

public class FonteRenovavelService {
    private final FonteRenovavelRepository fonteRenovavelRepository;

    public FonteRenovavelService() {
        this.fonteRenovavelRepository = new FonteRenovavelRepository();
    }

    public FonteRenovavel criarFonteRenovavel(String nome, String descricao) {
        FonteRenovavel fonte = new FonteRenovavel();
        fonte.setNome(nome);
        fonte.setDescricao(descricao);
        return fonteRenovavelRepository.salvar(fonte);
    }

    public Optional<FonteRenovavel> buscarPorId(Long id) {
        return fonteRenovavelRepository.buscarPorId(id);
    }

    public List<FonteRenovavel> listarTodas() {
        return fonteRenovavelRepository.buscarTodos();
    }

    public FonteRenovavel atualizarFonte(Long id, String nome, String descricao) throws Exception {
        FonteRenovavel fonte = fonteRenovavelRepository.buscarPorId(id).orElseThrow(() -> new Exception("Fonte não encontrada."));
        fonte.setNome(nome);
        fonte.setDescricao(descricao);
        return fonteRenovavelRepository.salvar(fonte);
    }

    public void deletarFonte(Long id) {
        fonteRenovavelRepository.deletar(id);
    }
}