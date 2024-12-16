package org.ms.spring.docker.usuarios.service.impl;

import org.ms.spring.docker.usuarios.client.FeignClientCursos;
import org.ms.spring.docker.usuarios.models.entity.UsuarioEntity;
import org.ms.spring.docker.usuarios.repository.UsuarioRepository;
import org.ms.spring.docker.usuarios.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final FeignClientCursos client;

    public UsuarioServiceImpl(UsuarioRepository repository, FeignClientCursos client){
        this.repository = repository;
        this.client = client;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> listar() {
        return (List<UsuarioEntity>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioEntity> buscarId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public UsuarioEntity guardarUsuario(UsuarioEntity usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional()
    public void eliminar(Long id) {

        repository.deleteById(id);
        client.eliminarUsuaroCurso(id);
    }

    @Override
    public Optional<UsuarioEntity> actualizarUsuario(UsuarioEntity usuarioUpd, Long id) {
        return repository.findById(id).map(
                usr -> {

                    usr.setEmail(usuarioUpd.getEmail());
                    usr.setNombre(usuarioUpd.getNombre());
                    usr.setPassword(usuarioUpd.getPassword());
                    return repository.save(usr);
                });
    }

    @Override
    public Optional<UsuarioEntity> buscarEmail(String email) {

        return repository.porEmail(email);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<UsuarioEntity> listarPorIds(Iterable<Long> ids) {
        return (List<UsuarioEntity>) repository.findAllById(ids);
    }


}
