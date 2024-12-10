package org.cursos.ms.app.cursos.ms.service;


import org.cursos.ms.app.cursos.ms.model.Usuario;
import org.cursos.ms.app.cursos.ms.model.entity.CursoEntity;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<CursoEntity> listar();
    Optional<CursoEntity> porId(Long id);
    CursoEntity guardarCurso(CursoEntity curso);
    void eliminarCurso(Long id);
    Optional<CursoEntity> actualizarCurso(Long id, CursoEntity curso);



}
