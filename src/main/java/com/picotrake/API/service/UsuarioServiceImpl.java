package com.picotrake.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;
import com.picotrake.API.mapper.UsuarioMapper;
import com.picotrake.API.model.Suscripciones;
import com.picotrake.API.model.Usuario;
import com.picotrake.API.repository.SuscripcionRepository;
import com.picotrake.API.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final SuscripcionRepository suscripcionRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SuscripcionRepository suscripcionRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.suscripcionRepository = suscripcionRepository;
        this.usuarioMapper = usuarioMapper;
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
        Suscripciones suscripcion = suscripcionRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Suscripción por defecto no encontrada"));
        usuario.setSuscripcion(suscripcion);    
        String hashedPassword = new BCryptPasswordEncoder().encode(usuarioCreate.getContrasena());
        usuario.setContrasena(hashedPassword);
        usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
}
