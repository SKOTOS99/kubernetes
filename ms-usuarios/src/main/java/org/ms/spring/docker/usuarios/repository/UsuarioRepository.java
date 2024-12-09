package org.ms.spring.docker.usuarios.repository;

import org.ms.spring.docker.usuarios.models.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {


    Optional<UsuarioEntity> findByEmail(String email);

    @Query(value = "select u from UsuarioEntity u where u.email=?1")
    Optional<UsuarioEntity> porEmail(String email);
    boolean existsByEmail(String email);
}
