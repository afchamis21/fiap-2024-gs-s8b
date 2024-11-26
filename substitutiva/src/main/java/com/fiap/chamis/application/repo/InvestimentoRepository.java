package com.fiap.chamis.application.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.Investimento;
import com.fiap.chamis.application.repo.core.BaseRepository;
import com.fiap.chamis.application.repo.core.IRepo;


public class InvestimentoRepository extends BaseRepository implements IRepo<Investimento, Long> {

    @Override
    public Investimento salvar(Investimento investimento) {
        String sql;
        if (investimento.getId() == null) {
            sql = """
                INSERT INTO Investimento (id, id_usuario, id_projeto_energia, valor)
                VALUES (investimento_seq.NEXTVAL, ?, ?, ?)
            """;
        } else {
            sql = """
                UPDATE Investimento
                SET id_usuario = ?, id_projeto_energia = ?, valor = ?
                WHERE id = ?
            """;
        }
    
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            ps.setLong(1, investimento.getIdUsuario());
            ps.setLong(2, investimento.getIdProjetoEnergia());
            ps.setBigDecimal(3, investimento.getValor());
    
            if (investimento.getId() != null) {
                ps.setLong(4, investimento.getId());
            }
    
            return investimento;
        } catch (SQLException e) {
            System.out.println("Erro salvando ou atualizando Investimento: " + e.getMessage());
            return null;
        }
    }    

    @Override
    public Optional<Investimento> buscarPorId(Long id) {
        String sql = "SELECT * FROM Investimento WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Investimento investimento = new Investimento();
                    investimento.setId(rs.getLong("id"));
                    investimento.setIdUsuario(rs.getLong("id_usuario"));
                    investimento.setIdProjetoEnergia(rs.getLong("id_projeto_energia"));
                    investimento.setValor(rs.getBigDecimal("valor"));
                    return Optional.of(investimento);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Investimento: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Investimento> buscarTodos() {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT * FROM Investimento";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(rs.getLong("id"));
                investimento.setIdUsuario(rs.getLong("id_usuario"));
                investimento.setIdProjetoEnergia(rs.getLong("id_projeto_energia"));
                investimento.setValor(rs.getBigDecimal("valor"));
                investimentos.add(investimento);
            }
        } catch (SQLException e) {
            System.out.println("Erro listando Investimentos: " + e.getMessage());
        }
        return investimentos;
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM Investimento WHERE id = ?";
    
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
    
            ps.setLong(1, id);
    
            ps.executeUpdate();
    
        } catch (SQLException e) {
            System.out.println("Erro deletando Investimento: " + e.getMessage());
        }
    }
}