package com.picotrake.API.service;

import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioResponse> getAllUsuarios();
    Optional<UsuarioResponse> getUsuarioById(Long id);
    UsuarioResponse createUsuario(UsuarioCreate usuarioCreate);
    void deleteUsuario(Long id);
}
