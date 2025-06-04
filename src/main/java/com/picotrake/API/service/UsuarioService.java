package com.picotrake.API.service;

import com.picotrake.API.dto.UpdatePasswordRequest;
import com.picotrake.API.dto.UpdateSuscriptionUserModel;
import com.picotrake.API.dto.UpdateUserRequest;
import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioResponse> getAllUsuarios();
    Optional<UsuarioResponse> getUsuarioById(Long id);
    UsuarioResponse createUsuario(UsuarioCreate usuarioCreate);
    void deleteMyAccount(Long userId, String rawPassword);
    void updateSuscription(Long id, UpdateSuscriptionUserModel request);
    void updateUser(Long id, UpdateUserRequest request);
    void updatePassword(Long id, UpdatePasswordRequest request);
}