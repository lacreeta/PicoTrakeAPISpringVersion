package com.picotrake.API.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;
import com.picotrake.API.mapper.UsuarioMapper;
import com.picotrake.API.model.Suscripciones;
import com.picotrake.API.model.Usuario;
import com.picotrake.API.repository.SuscripcionRepository;
import com.picotrake.API.repository.UsuarioRepository;
import com.picotrake.API.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final SuscripcionRepository suscripcionRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SuscripcionRepository suscripcionRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.suscripcionRepository = suscripcionRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioMapper.toResponse(usuarioRepository.findAll());
    }

    @Override
    public Optional<UsuarioResponse> getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toResponse);
    }

    @Override
    public UsuarioResponse createUsuario(UsuarioCreate usuarioCreate) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreate);
        Suscripciones suscripcion = suscripcionRepository.findById((long)1)
                .orElseThrow(() -> new RuntimeException("Suscripción por defecto no encontrada"));
        usuario.setSuscripcion(suscripcion);    
        String hashedPassword = passwordEncoder.encode(usuarioCreate.getContrasena());
        usuario.setContrasena(hashedPassword);
        usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public void deleteMyAccount(Long userId, String rawPassword) {
        Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        System.out.println("🔐 Contraseña recibida: " + rawPassword);
        System.out.println("🔐 Hash en BD: " + usuario.getContrasena());
        System.out.println("🔐 Comparación: " + passwordEncoder.matches(rawPassword, usuario.getContrasena()));

        if (!passwordEncoder.matches(rawPassword, usuario.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        usuarioRepository.deleteById(userId);
    }
}
