package org.ms.spring.docker.usuarios.controller;


import org.ms.spring.docker.usuarios.models.entity.UsuarioEntity;
import org.ms.spring.docker.usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UsuariosController {

    private final UsuarioService service;

    public UsuariosController(UsuarioService service){
        this.service = service;
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios(){
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/id/{id}")
    public ResponseEntity<UsuarioEntity> buscarId(@PathVariable long id){

        Optional<UsuarioEntity> usuario = service.buscarId(id);
        return usuario.map(usuarioEntity ->
                new ResponseEntity<>(usuarioEntity, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar/usuario")
    public ResponseEntity<UsuarioEntity> guardarUsuario(@RequestBody UsuarioEntity usuario){
        return new ResponseEntity<>(service.guardarUsuario(usuario), HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/usuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
