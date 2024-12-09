package org.ms.spring.docker.usuarios.controller;


import jakarta.validation.Valid;
import org.ms.spring.docker.usuarios.models.entity.UsuarioEntity;
import org.ms.spring.docker.usuarios.service.UsuarioService;
import org.ms.spring.docker.usuarios.validate.ValidateErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class UsuariosController {

    private final UsuarioService service;

    private final ValidateErrors errorsValid;

    public UsuariosController(UsuarioService service, ValidateErrors errorsValid){
        this.service = service;
        this.errorsValid = errorsValid;
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
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar/usuario")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody UsuarioEntity usuario, BindingResult result){
        boolean usuarioExiste = service.existePorEmail(usuario.getEmail());
        if(result.hasErrors()){
            return errorsValid.getMapResponseEntity(result);
        }
        if(usuarioExiste){
            return new ResponseEntity<>(Collections.singletonMap("error","El email ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.guardarUsuario(usuario), HttpStatus.OK);
    }



    @DeleteMapping(value = "/eliminar/usuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        Optional<UsuarioEntity> usuario = service.buscarId(id);
        if(usuario.isPresent()){
            service.eliminar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/actualizar/usuario/{id}")
    public ResponseEntity<?> actualizarUsuario(
            @Valid @PathVariable Long id,
            @RequestBody UsuarioEntity usuario,
            BindingResult result) {

        if (result.hasErrors()) {
            return errorsValid.getMapResponseEntity(result);
        }
        Optional<UsuarioEntity> usuarioExiste = service.buscarEmail(usuario.getEmail());
        if (usuarioExiste.isPresent() && !usuarioExiste.get().getId().equals(id)) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("Error", "Ese correo ya está registrado"));
        }

        return service.actualizarUsuario(usuario, id)
                .map(usuarioActualizado -> new ResponseEntity<>(usuarioActualizado, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
