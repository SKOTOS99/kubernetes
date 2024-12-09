package org.cursos.ms.app.cursos.ms.reporsitory;


import org.cursos.ms.app.cursos.ms.model.entity.CursoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CursosRespository extends CrudRepository<CursoEntity, Long> {
}
