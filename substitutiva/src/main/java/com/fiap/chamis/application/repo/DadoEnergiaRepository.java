package com.fiap.chamis.application.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.DadoEnergia;
import com.fiap.chamis.application.repo.core.BaseRepository;
import com.fiap.chamis.application.repo.core.IRepo;

public class DadoEnergiaRepository extends BaseRepository implements IRepo<DadoEnergia, Long> {

    @Override
    public DadoEnergia salvar(DadoEnergia dadoEnergia) {
        String sql;
    
        if (dadoEnergia.getId() == null) {
            sql = """
                INSERT INTO DadoEnergia (id, id_projeto_energia, energia_gerada, data)
                VALUES (dado_energia_seq.NEXTVAL, ?, ?, ?)
            """;
        } else {
            sql = """
                UPDATE DadoEnergia
                SET id_projeto_energia = ?, energia_gerada = ?, data = ?
                WHERE id = ?
            """;
        }
    
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            ps.setLong(1, dadoEnergia.getIdProjetoEnergia());
            ps.setDouble(2, dadoEnergia.getEnergiaGerada());
            ps.setDate(3, Date.valueOf(dadoEnergia.getData()));
    
            if (dadoEnergia.getId() != null) {
                ps.setLong(4, dadoEnergia.getId());
            }
    
            return dadoEnergia;
    
        } catch (SQLException e) {
            System.out.println("Erro salvando ou atualizando Dado de Energia: " + e.getMessage());
            return null;
        }
    }
    

    @Override
    public Optional<DadoEnergia> buscarPorId(Long id) {
        String sql = "SELECT id, id_projeto_energia, energia_gerada, data FROM DadoEnergia WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DadoEnergia dadoEnergia = new DadoEnergia();
                dadoEnergia.setId(rs.getLong("id"));
                dadoEnergia.setIdProjetoEnergia(rs.getLong("id_projeto_energia"));
                dadoEnergia.setEnergiaGerada(rs.getDouble("energia_gerada"));
                dadoEnergia.setData(rs.getDate("data").toLocalDate());
                return Optional.of(dadoEnergia);
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Dado de Energia: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<DadoEnergia> buscarTodos() {
        String sql = "SELECT id, id_projeto_energia, energia_gerada, data FROM DadoEnergia";
        List<DadoEnergia> dadosEnergia = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DadoEnergia dadoEnergia = new DadoEnergia();
                dadoEnergia.setId(rs.getLong("id"));
                dadoEnergia.setIdProjetoEnergia(rs.getLong("id_projeto_energia"));
                dadoEnergia.setEnergiaGerada(rs.getDouble("energia_gerada"));
                dadoEnergia.setData(rs.getDate("data").toLocalDate());
                dadosEnergia.add(dadoEnergia);
            }
        } catch (SQLException e) {
            System.out.println("Erro listando Dados de Energia: " + e.getMessage());
        }
        return dadosEnergia;
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM DadoEnergia WHERE id = ?";
    
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
    
            ps.setLong(1, id);
    
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro apagando Dado de Energia: " + e.getMessage());
        }
    }
}

