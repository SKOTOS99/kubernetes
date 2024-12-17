package org.ms.spring.docker.usuarios.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cursos", url = "host.docker.internal:8002")
public interface FeignClientCursos {

    @DeleteMapping(value = "/api/eliminar/curso/usuario/{id}")
    void eliminarUsuaroCurso(@PathVariable Long id);
}
