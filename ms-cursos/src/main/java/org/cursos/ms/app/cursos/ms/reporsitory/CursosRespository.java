package org.cursos.ms.app.cursos.ms.reporsitory;


import org.cursos.ms.app.cursos.ms.model.entity.CursoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursosRespository extends CrudRepository<CursoEntity, Long> {

    @Modifying
    @Query("delete from CursoUsuarioEntity cu where cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long id);

}
