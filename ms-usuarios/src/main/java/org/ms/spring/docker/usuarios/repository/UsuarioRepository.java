package org.ms.spring.docker.usuarios.repository;

import org.ms.spring.docker.usuarios.models.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {


    Optional<UsuarioEntity> findByEmail(String email);
}
