package org.ms.spring.docker.usuarios.service;

import org.ms.spring.docker.usuarios.models.entity.UsuarioEntity;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioEntity> listar();

    Optional<UsuarioEntity> buscarId(Long id);

    UsuarioEntity guardarUsuario(UsuarioEntity usuario);

    void eliminar(Long id);

    Optional<UsuarioEntity> actualizarUsuario(UsuarioEntity usuario, Long id);

    Optional<UsuarioEntity> buscarEmail(String email);

    boolean existePorEmail(String email);

    List<UsuarioEntity> listarPorIds(Iterable<Long> ids);
}
