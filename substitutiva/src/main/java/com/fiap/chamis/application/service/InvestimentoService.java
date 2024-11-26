package com.fiap.chamis.application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.Investimento;
import com.fiap.chamis.application.repo.InvestimentoRepository;

public class InvestimentoService {
    private final InvestimentoRepository investimentoRepository;

    public InvestimentoService() {
        this.investimentoRepository = new InvestimentoRepository();
    }

    public Investimento criarInvestimento(Long idUsuario, Long idProjetoEnergia, BigDecimal valor) {
        Investimento investimento = new Investimento();
        investimento.setIdUsuario(idUsuario);
        investimento.setIdProjetoEnergia(idProjetoEnergia);
        investimento.setValor(valor);
        return investimentoRepository.salvar(investimento);
    }

    public Optional<Investimento> buscarPorId(Long id) {
        return investimentoRepository.buscarPorId(id);
    }

    public List<Investimento> listarTodos() {
        return investimentoRepository.buscarTodos();
    }

    public Investimento atualizarInvestimento(Long id, BigDecimal novoValor) throws Exception {
        Investimento investimento = investimentoRepository.buscarPorId(id).orElseThrow(() -> new Exception("Investimento não encontrado."));
        investimento.setValor(novoValor);
        return investimentoRepository.salvar(investimento);
    }

    public void deletarInvestimento(Long id) {
        investimentoRepository.deletar(id);
    }
}