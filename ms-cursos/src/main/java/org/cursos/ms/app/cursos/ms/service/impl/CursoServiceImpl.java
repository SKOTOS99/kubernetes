package org.cursos.ms.app.cursos.ms.service.impl;

import org.cursos.ms.app.cursos.ms.client.FeigClientUsuariosMs;
import org.cursos.ms.app.cursos.ms.model.Usuario;
import org.cursos.ms.app.cursos.ms.model.entity.CursoEntity;
import org.cursos.ms.app.cursos.ms.model.entity.CursoUsuarioEntity;
import org.cursos.ms.app.cursos.ms.reporsitory.CursosRespository;
import org.cursos.ms.app.cursos.ms.service.CursoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursosRespository repo;

    private final FeigClientUsuariosMs client;

    public CursoServiceImpl(CursosRespository repo, FeigClientUsuariosMs client){
        this.repo = repo;
        this.client = client;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoEntity> listar() {
        return (List<CursoEntity>) repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CursoEntity> porId(Long id) {
        return repo.findById(id);
    }

    @Override
    @Transactional()
    public CursoEntity guardarCurso(CursoEntity curso) {
        return repo.save(curso);
    }

    @Override
    @Transactional()
    public void eliminarCurso(Long id) {

            repo.deleteById(id);
    }

    @Override
    @Transactional()
    public Optional<CursoEntity> actualizarCurso(Long id, CursoEntity curso) {
        return repo.findById(id).map(curso1 -> {
            curso1.setNombre(curso.getNombre());
            return repo.save(curso1);
        });

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CursoEntity> porIdUsuarios(Long id) {
        Optional<CursoEntity> detalleCurso = repo.findById(id);
        if (detalleCurso.isPresent()){
            CursoEntity curso = detalleCurso.get();
            if (!curso.getCursoUsuarios().isEmpty()){
                List<Long> ids = curso.getCursoUsuarios().stream().map(CursoUsuarioEntity::getUsuarioId).toList();
                List<Usuario> usuarios = client.listarPorIds(ids);
                curso.setUsuarios(usuarios);
            }
            return Optional.of(curso);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void eliminarCursoUsuarioPorId(Long id) {
        repo.eliminarCursoUsuarioPorId(id);
    }

}
