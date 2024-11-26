package com.fiap.chamis.application.service;

import java.util.List;
import java.util.Optional;

import com.fiap.chamis.application.domain.Usuario;
import com.fiap.chamis.application.repo.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public Usuario criarUsuario(String nomeUsuario, String email, String senha) throws Exception {
        if (usuarioRepository.existePorEmail(email)) {
            throw new Exception("Usuário já cadastrado com esse email!");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuarioRepository.salvar(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario atualizarUsuario(Long id, String nomeUsuario, String email, String senha) throws Exception {
        Usuario usuario = usuarioRepository.buscarPorId(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
        if (email != null && !email.equals(usuario.getEmail()) && usuarioRepository.existePorEmail(email)) {
            throw new Exception("Usuário já cadastrado com esse email!");
        }

        usuario.setNomeUsuario(nomeUsuario);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuarioRepository.salvar(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deletar(id);
    }

    public Usuario autenticarUsuario(String email, String senha) throws Exception {
        Usuario usuario = usuarioRepository.buscarPorEmail(email).orElseThrow(() -> new Exception("Credenciais inválidas"));
        
        if (!usuario.getSenha().equals(senha)) {
            throw new Exception("Credenciais inválidas");
        }

        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.buscarTodos();
    }
}