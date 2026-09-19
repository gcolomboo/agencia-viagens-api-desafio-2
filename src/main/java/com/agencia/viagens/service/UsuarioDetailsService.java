package com.agencia.viagens.service;

import com.agencia.viagens.model.Usuario;
import com.agencia.viagens.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return new User(
                usuario.getUsername(),
                usuario.getSenha(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()))
        );
    }
}
