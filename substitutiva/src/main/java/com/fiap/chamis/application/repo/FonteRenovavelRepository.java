package com.fiap.chamis.application.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.FonteRenovavel;
import com.fiap.chamis.application.repo.core.BaseRepository;
import com.fiap.chamis.application.repo.core.IRepo;

public class FonteRenovavelRepository extends BaseRepository implements IRepo<FonteRenovavel, Long> {

    @Override
    public FonteRenovavel salvar(FonteRenovavel fonte) {
        String sql;

        if (fonte.getId() == null) {
            sql = """
                    INSERT INTO FonteRenovavel (id, nome, descricao)
                    VALUES (fonte_renovavel_seq.NEXTVAL, ?, ?)
                """;
        } else {
            sql = """
                    UPDATE FonteRenovavel
                    SET nome = ?, descricao = ?
                    WHERE id = ?
                """;
        }

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, fonte.getNome());
            ps.setString(2, fonte.getDescricao());

            if (fonte.getId() != null) {
                ps.setLong(3, fonte.getId());
            }

            return fonte;

        } catch (SQLException e) {
            System.out.println("Erro salvando ou atualizando Fonte Renovável: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<FonteRenovavel> buscarPorId(Long id) {
        String sql = "SELECT id, nome, descricao FROM FonteRenovavel WHERE id = ?";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                FonteRenovavel fonte = new FonteRenovavel();
                fonte.setId(rs.getLong("id"));
                fonte.setNome(rs.getString("nome"));
                fonte.setDescricao(rs.getString("descricao"));
                return Optional.of(fonte);
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Fonte Renovável: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<FonteRenovavel> buscarTodos() {
        String sql = "SELECT id, nome, descricao FROM FonteRenovavel";
        List<FonteRenovavel> fontes = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FonteRenovavel fonte = new FonteRenovavel();
                fonte.setId(rs.getLong("id"));
                fonte.setNome(rs.getString("nome"));
                fonte.setDescricao(rs.getString("descricao"));
                fontes.add(fonte);
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Fontes Renováveis: " + e.getMessage());
        }
        return fontes;
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM FonteRenovavel WHERE id = ?";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro deletando Fonte Renovável: " + e.getMessage());
        }
    }
}
