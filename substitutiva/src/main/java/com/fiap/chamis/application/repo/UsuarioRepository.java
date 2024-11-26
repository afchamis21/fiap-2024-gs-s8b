package com.fiap.chamis.application.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.Usuario;
import com.fiap.chamis.application.repo.core.BaseRepository;
import com.fiap.chamis.application.repo.core.IRepo;

public class UsuarioRepository extends BaseRepository implements IRepo<Usuario, Long> {

    @Override
    public Usuario salvar(Usuario usuario) {
        String sql;
        if (usuario.getId() == null) {
            sql = """
                        INSERT INTO Usuario (id, nome_usuario, email, senha)
                        VALUES (usuario_seq.NEXTVAL, ?, ?, ?)
                    """;
        } else {
            sql = """
                        UPDATE Usuario
                        SET nome_usuario = ?, email = ?, senha = ?
                        WHERE id = ?
                    """;
        }

        try (Connection connection = super.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            if (usuario.getId() != null) {
                ps.setLong(4, usuario.getId());
            }

            return usuario;
        } catch (SQLException e) {
            System.out.println("Erro salvando ou atualizando Usuário: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (Connection connection = super.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNomeUsuario(rs.getString("nome_usuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Usuário: " + e.getMessage());
        }
        return Optional.empty();
    }

    public boolean existePorEmail(String email) {
        String sql = "SELECT 1 FROM Usuario WHERE email = ?";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Usuário: " + e.getMessage());
        }
        return false;
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNomeUsuario(rs.getString("nome_usuario"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro buscando Usuário: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro deletando Usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro listando Projetos de Energia: " + e.getMessage());
        }
        return usuarios;
    }
}
