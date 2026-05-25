package com.usuario.Microservicio_usuario.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(length = 100, nullable = false)
    private String nomUsuario;

    @Column(length = 150, nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    @Enumerated(EnumType.STRING)
    private RolAsignado rolAsignado;

    @Column(length = 15)
    private String telefono;

    @Column(length = 250)
    private String direccion;

    public enum EstadoUsuario {
        ACTIVO, INACTIVO, BLOQUEADO
    }

    public enum RolAsignado {
        ADMINISTRADOR, GERENTE_SUCURSAL, EMPLEADO_VENTAS, CLIENTE
    }
}
