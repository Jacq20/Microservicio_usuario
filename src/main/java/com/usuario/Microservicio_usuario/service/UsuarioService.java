package com.usuario.Microservicio_usuario.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.Microservicio_usuario.DTO.UsuarioDTO;
import com.usuario.Microservicio_usuario.model.Usuario;
import com.usuario.Microservicio_usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearCuenta(UsuarioDTO dto) {
        try {
            if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
                throw new RuntimeException("El correo ya está registrado");
            }
            Usuario usuario = new Usuario();
            usuario.setNomUsuario(dto.getNomUsuario());
            usuario.setCorreo(dto.getCorreo());
            usuario.setContrasena(dto.getContrasena());
            usuario.setTelefono(dto.getTelefono());
            usuario.setDireccion(dto.getDireccion());
            usuario.setFechaCreacion(LocalDateTime.now());
            usuario.setEstado(Usuario.EstadoUsuario.ACTIVO);
            usuario.setRolAsignado(Usuario.RolAsignado.CLIENTE);
            return usuarioRepository.save(usuario);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> consultarPerfil(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> editarPerfil(Long id, UsuarioDTO dto) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            usuario.ifPresent(u -> {
                u.setNomUsuario(dto.getNomUsuario());
                u.setTelefono(dto.getTelefono());
                u.setDireccion(dto.getDireccion());
                usuarioRepository.save(u);
            });
            return usuario;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Optional<Usuario> asignarRol(Long id, String rol) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            usuario.ifPresent(u -> {
                u.setRolAsignado(Usuario.RolAsignado.valueOf(rol));
                usuarioRepository.save(u);
            });
            return usuario;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public boolean eliminarCuenta(Long id) {
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.findById(id).ifPresent(u -> {
                    u.setEstado(Usuario.EstadoUsuario.INACTIVO);
                    usuarioRepository.save(u);
                });
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
