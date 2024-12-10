package org.cursos.ms.app.cursos.ms.service;

import org.cursos.ms.app.cursos.ms.model.Usuario;

import java.util.Optional;

public interface CursoUsuariosService {

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);
}
