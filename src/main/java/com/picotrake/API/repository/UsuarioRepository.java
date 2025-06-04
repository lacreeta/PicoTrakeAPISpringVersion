package com.picotrake.API.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.picotrake.API.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);  
    boolean existsByEmail(String email);
    @Query("SELECT u.nombre FROM Usuario u WHERE u.id_usuarios = :id")
    String findNombreById(@Param("id") Long id);
}