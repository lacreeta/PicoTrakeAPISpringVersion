package com.picotrake.API.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.picotrake.API.dto.HistorialActividadResponse;
import com.picotrake.API.model.HistorialActividad;

public interface HistorialRepository extends JpaRepository<HistorialActividad, Long> {
    
    @Query(value = """
        SELECT 
            h.id_ruta AS id_ruta,
            r.nombre_ruta AS nombre_ruta,
            h.fecha AS fecha
        FROM historial_actividades h
        JOIN rutas r ON h.id_ruta = r.id_ruta
        WHERE h.id_usuarios = :idUsuario
        ORDER BY h.fecha DESC
        """, nativeQuery = true)
    List<HistorialActividadResponse> getHistorialByUsuario(@Param("idUsuario") Long idUsuario);

    @Query(value = """
        SELECT h.id_ruta AS id_ruta, r.nombre_ruta AS nombre_ruta, h.fecha AS fecha
        FROM historial_actividades h
        JOIN rutas r ON h.id_ruta = r.id_ruta
        WHERE h.id_usuarios = :userId AND LOWER(r.nombre_ruta) LIKE LOWER(CONCAT('%', :nombreRuta, '%'))
    """, nativeQuery = true)
    List<HistorialActividadResponse> getHistorialByNombreRuta(
        @Param("userId") Long userId,
        @Param("nombreRuta") String nombreRuta
    );

    @Query(value = """
    SELECT h.id_ruta AS id_ruta, r.nombre_ruta AS nombre_ruta, h.fecha AS fecha
    FROM historial_actividades h
    JOIN rutas r ON h.id_ruta = r.id_ruta
    WHERE h.id_usuarios = :userId
    AND h.fecha BETWEEN :fechaInicio AND :fechaFin
    ORDER BY h.fecha DESC
    LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
    List<HistorialActividadResponse> getHistorialByFecha(
        @Param("userId") Long userId,
        @Param("fechaInicio") Date fechaInicio,
        @Param("fechaFin") Date fechaFin,
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM historial_actividades WHERE id_usuarios = :userId", nativeQuery = true)
    void deleteHistorialByUsuario(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM historial_actividades
        WHERE id_usuarios = :userId 
        AND id_ruta IN (
            SELECT id_ruta FROM rutas WHERE nombre_ruta = :nombreRuta
        )
    """, nativeQuery = true)
    void deleteHistorialByNombreRuta(
        @Param("userId") Long userId,
        @Param("nombreRuta") String nombreRuta
    );

}