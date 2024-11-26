package com.fiap.chamis.application.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.ProjetoEnergia;
import com.fiap.chamis.application.repo.core.BaseRepository;
import com.fiap.chamis.application.repo.core.IRepo;

public class ProjetoEnergiaRepository extends BaseRepository implements IRepo<ProjetoEnergia, Long> {

    @Override
    public ProjetoEnergia salvar(ProjetoEnergia projeto) {
        String sql;
        if (projeto.getId() == null) {
            sql = """
                        INSERT INTO ProjetoEnergia (id, nome, localizacao, capacidade, id_fonte_renovavel)
                        VALUES (projeto_energia_seq.NEXTVAL, ?, ?, ?, ?)
                    """;
        } else {
            sql = """
                        UPDATE ProjetoEnergia
                        SET nome = ?, localizacao = ?, capacidade = ?, id_fonte_renovavel = ?
                        WHERE id = ?
                    """;
        }

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, projeto.getNome());
            ps.setString(2, projeto.getLocalizacao());
            ps.setDouble(3, projeto.getCapacidade());
            ps.setLong(4, projeto.getIdFonteRenovavel());

            if (projeto.getId() != null) {
                ps.setLong(5, projeto.getId());
            }

            return projeto;
        } catch (SQLException e) {
            System.out.println("Erro salvando ou atualizando Projeto de Energia: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<ProjetoEnergia> buscarPorId(Long id) {
        String sql = "SELECT * FROM ProjetoEnergia WHERE id = ?";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProjetoEnergia projeto = new ProjetoEnergia();
                    projeto.setId(rs.getLong("id"));
                    projeto.setNome(rs.getString("nome"));
                    projeto.setLocalizacao(rs.getString("localizacao"));
                    projeto.setCapacidade(rs.getDouble("capacidade"));
                    projeto.setIdFonteRenovavel(rs.getLong("id_fonte_renovavel"));
                    return Optional.of(projeto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Projeto de Energia: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<ProjetoEnergia> buscarTodos() {
        List<ProjetoEnergia> projetos = new ArrayList<>();
        String sql = "SELECT * FROM ProjetoEnergia";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProjetoEnergia projeto = new ProjetoEnergia();
                projeto.setId(rs.getLong("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setLocalizacao(rs.getString("localizacao"));
                projeto.setCapacidade(rs.getDouble("capacidade"));
                projeto.setIdFonteRenovavel(rs.getLong("id_fonte_renovavel"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            System.out.println("Erro listando Projetos de Energia: " + e.getMessage());
        }
        return projetos;
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM ProjetoEnergia WHERE id = ?";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro deletando Projeto de Energia: " + e.getMessage());
        }
    }
}
