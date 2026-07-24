package com.guilhermelevi.agendadortarefas.infrastructure.security;

import com.guilhermelevi.agendadortarefas.business.dto.UsuarioDTO;
import com.guilhermelevi.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    @Autowired
    private UsuarioClient usuarioClient;

    public UserDetails carregaDaddosUsuario(String email, String token) {

        UsuarioDTO usuarioDTO= usuarioClient.buscaUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build();
    }
}
