package org.ms.spring.docker.usuarios.repository;

import org.ms.spring.docker.usuarios.models.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
}
