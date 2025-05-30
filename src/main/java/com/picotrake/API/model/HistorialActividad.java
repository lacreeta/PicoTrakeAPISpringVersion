package com.picotrake.API.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="historial_actividades")
public class HistorialActividad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_historial;

    @ManyToOne
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;

    @Column(nullable = true)
    private Date fecha;
}
