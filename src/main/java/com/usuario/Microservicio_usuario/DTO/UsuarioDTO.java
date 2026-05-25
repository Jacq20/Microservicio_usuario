package com.usuario.Microservicio_usuario.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nomUsuario;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene formato válido")
    @Size(max = 150, message = "Máximo 150 caracteres")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "Mínimo 6 caracteres")
    private String contrasena;

    @Size(max = 15, message = "Máximo 15 caracteres")
    private String telefono;

    @Size(max = 250, message = "Máximo 250 caracteres")
    private String direccion;
}
