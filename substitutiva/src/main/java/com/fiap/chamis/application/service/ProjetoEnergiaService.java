package com.fiap.chamis.application.service;

import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.FonteRenovavel;
import com.fiap.chamis.application.domain.ProjetoEnergia;
import com.fiap.chamis.application.repo.FonteRenovavelRepository;
import com.fiap.chamis.application.repo.ProjetoEnergiaRepository;

public class ProjetoEnergiaService {
    private final ProjetoEnergiaRepository projetoEnergiaRepository;
    private final FonteRenovavelRepository fonteRenovavelRepository;

    public ProjetoEnergiaService() {
        this.projetoEnergiaRepository = new ProjetoEnergiaRepository();
        this.fonteRenovavelRepository = new FonteRenovavelRepository();
    }

    public ProjetoEnergia criarProjeto(String nome, String localizacao, double capacidade, Long idFonteRenovavel) throws Exception {
        FonteRenovavel fonte = fonteRenovavelRepository.buscarPorId(idFonteRenovavel).orElseThrow(() -> new Exception("Fonte não encontrada."));
        ProjetoEnergia projeto = new ProjetoEnergia();
        projeto.setNome(nome);
        projeto.setLocalizacao(localizacao);
        projeto.setCapacidade(capacidade);
        projeto.setIdFonteRenovavel(fonte.getId());
        return projetoEnergiaRepository.salvar(projeto);
    }

    public Optional<ProjetoEnergia> buscarPorId(Long id) {
        return projetoEnergiaRepository.buscarPorId(id);
    }

    public List<ProjetoEnergia> listarTodos() {
        return projetoEnergiaRepository.buscarTodos();
    }

    public ProjetoEnergia atualizarProjeto(Long id, String nome, String localizacao, double capacidade, Long idFonteRenovavel) throws Exception {
        ProjetoEnergia projeto = projetoEnergiaRepository.buscarPorId(id).orElseThrow(() -> new Exception("Projeto não encontrado."));
        FonteRenovavel fonte = fonteRenovavelRepository.buscarPorId(idFonteRenovavel).orElseThrow(() -> new Exception("Fonte não encontrada."));
        projeto.setNome(nome);
        projeto.setLocalizacao(localizacao);
        projeto.setCapacidade(capacidade);
        projeto.setIdFonteRenovavel(fonte.getId());
        return projetoEnergiaRepository.salvar(projeto);
    }

    public void deletarProjeto(Long id) {
        projetoEnergiaRepository.deletar(id);
    }
}