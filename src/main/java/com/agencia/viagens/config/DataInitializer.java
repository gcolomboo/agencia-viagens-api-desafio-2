package com.agencia.viagens.config;

import com.agencia.viagens.model.Perfil;
import com.agencia.viagens.model.Usuario;
import com.agencia.viagens.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner inicializarUsuarios(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                usuarioRepository.save(
                    new Usuario("admin", passwordEncoder.encode("admin123"), Perfil.ADMIN)
                );
            }

            if (usuarioRepository.findByUsername("usuario").isEmpty()) {
                usuarioRepository.save(
                    new Usuario("usuario", passwordEncoder.encode("user123"), Perfil.USER)
                );
            }
        };
    }
}
