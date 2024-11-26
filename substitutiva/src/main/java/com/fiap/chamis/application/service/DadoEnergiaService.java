package com.fiap.chamis.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.DadoEnergia;
import com.fiap.chamis.application.repo.DadoEnergiaRepository;

public class DadoEnergiaService {
    private final DadoEnergiaRepository dadoEnergiaRepository;

    public DadoEnergiaService() {
        this.dadoEnergiaRepository = new DadoEnergiaRepository();
    }

    public DadoEnergia criarDadoEnergia(Long idProjetoEnergia, Double energiaGerada, LocalDate data) {
        DadoEnergia dadoEnergia = new DadoEnergia();
        dadoEnergia.setIdProjetoEnergia(idProjetoEnergia);
        dadoEnergia.setEnergiaGerada(energiaGerada);
        dadoEnergia.setData(data);
        return dadoEnergiaRepository.salvar(dadoEnergia);
    }

    public Optional<DadoEnergia> buscarPorId(Long id) {
        return dadoEnergiaRepository.buscarPorId(id);
    }

    public List<DadoEnergia> listarTodos() {
        return dadoEnergiaRepository.buscarTodos();
    }

    public DadoEnergia atualizarDadoEnergia(Long id, Long idProjetoEnergia, Double energiaGerada, LocalDate data) throws Exception {
        DadoEnergia dadoEnergia = dadoEnergiaRepository.buscarPorId(id).orElseThrow(() -> new Exception("Dado de energia não encontrado."));
        dadoEnergia.setIdProjetoEnergia(idProjetoEnergia);
        dadoEnergia.setEnergiaGerada(energiaGerada);
        dadoEnergia.setData(data);
        return dadoEnergiaRepository.salvar(dadoEnergia);
    }

    public void deletarDadoEnergia(Long id) {
        dadoEnergiaRepository.deletar(id);
    }
}