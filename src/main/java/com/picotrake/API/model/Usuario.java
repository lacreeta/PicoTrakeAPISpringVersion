package com.picotrake.API.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuarios;
    
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private Date fecha_registro;

    @OneToOne
    @JoinColumn(name = "id_suscripciones", referencedColumnName = "id_suscripciones", nullable = false)
    private Suscripciones suscripcion;

    @OneToMany(mappedBy = "usuario")
    private List<HistorialActividad> historialActividades;


    private Date fecha_inicio_suscripcion;
    private Date fecha_final_suscripcion;
}