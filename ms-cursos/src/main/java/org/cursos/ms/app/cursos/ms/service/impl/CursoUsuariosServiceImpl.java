package org.cursos.ms.app.cursos.ms.service.impl;

import org.cursos.ms.app.cursos.ms.client.FeigClientUsuariosMs;
import org.cursos.ms.app.cursos.ms.model.Usuario;
import org.cursos.ms.app.cursos.ms.model.entity.CursoEntity;
import org.cursos.ms.app.cursos.ms.model.entity.CursoUsuarioEntity;
import org.cursos.ms.app.cursos.ms.reporsitory.CursosRespository;
import org.cursos.ms.app.cursos.ms.service.CursoUsuariosService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CursoUsuariosServiceImpl implements CursoUsuariosService {

    private final CursosRespository respository;

    private final FeigClientUsuariosMs client;


    public CursoUsuariosServiceImpl(CursosRespository respository, FeigClientUsuariosMs client){
        this.respository = respository;
        this.client = client;
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        Optional<CursoEntity>  cursoBd = respository.findById(cursoId);
        if (cursoBd.isPresent()) {
            Usuario usuarioBd = client.detalleId(usuario.getId());
            CursoEntity asignarCurso = cursoBd.get();
            CursoUsuarioEntity asignarUsuario =new CursoUsuarioEntity();
            asignarUsuario.setUsuarioId(usuarioBd.getId());
            asignarCurso.addCursoUsuario(asignarUsuario);
            respository.save(asignarCurso);
            return Optional.of(usuario);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<CursoEntity>  cursoBd = respository.findById(cursoId);
        if (cursoBd.isPresent()) {
            Usuario usuarioBd = client.crearUsuario(usuario);
            CursoEntity asignarCurso = cursoBd.get();
            CursoUsuarioEntity asignarUsuario =new CursoUsuarioEntity();
            asignarUsuario.setUsuarioId(usuarioBd.getId());
            asignarCurso.addCursoUsuario(asignarUsuario);
            respository.save(asignarCurso);
            return Optional.of(usuario);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        Optional<CursoEntity>  cursoBd = respository.findById(cursoId);
        if (cursoBd.isPresent()) {
            Usuario usuarioBd = client.detalleId(usuario.getId());
            CursoEntity asignarCurso = cursoBd.get();
            CursoUsuarioEntity asignarUsuario =new CursoUsuarioEntity();
            asignarUsuario.setUsuarioId(usuarioBd.getId());
            asignarCurso.removeCursoUsuario(asignarUsuario);
            respository.save(asignarCurso);
            return Optional.of(usuario);
        }

        return Optional.empty();
    }


}
