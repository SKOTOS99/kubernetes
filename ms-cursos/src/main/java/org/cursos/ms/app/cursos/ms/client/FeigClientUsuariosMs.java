package org.cursos.ms.app.cursos.ms.client;


import org.cursos.ms.app.cursos.ms.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-usuarios", url = "${app.usuarios.url}")
public interface FeigClientUsuariosMs {

    @GetMapping(value = "/api/listar/id/{id}")
    Usuario detalleId(@PathVariable Long id);

    @PostMapping(value = "/api/guardar/usuario")
    Usuario crearUsuario(@RequestBody Usuario usuario);

    @DeleteMapping(value = "/api/eliminar/usuario/{id}")
    void eliminarUsuario(@PathVariable Long id);

    @GetMapping(value = "/api/usuarios-curso")
    List<Usuario> listarPorIds(@RequestParam Iterable<Long> ids);

}
