package com.example.service;

import com.example.dto.UsuarioDto;
import com.example.model.Rol;
import java.util.*;
import java.util.stream.Collectors;
import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("usuarioDetailsService")
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario save(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.getUsername(), usuarioDto.getEmail(),
                passwordEncoder.encode(usuarioDto.getPassword()), Arrays.asList(new Rol("ROL_USER")));
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Username o Password incorrecto");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
}
