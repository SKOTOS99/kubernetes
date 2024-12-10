package org.cursos.ms.app.cursos.ms.controller;


import feign.FeignException;
import org.cursos.ms.app.cursos.ms.model.Usuario;
import org.cursos.ms.app.cursos.ms.service.CursoUsuariosService;
import org.cursos.ms.app.cursos.ms.validate.ValidateErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class AsignarUsuariosCursosController {

    private final CursoUsuariosService service;

    private final ValidateErrors errorsValid;

    public AsignarUsuariosCursosController(CursoUsuariosService service, ValidateErrors errorsValid){
        this.service = service;
        this.errorsValid = errorsValid;
    }

    @PutMapping(value = "/asignar/usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@PathVariable Long cursoId, @RequestBody Usuario usuario){
        Optional<Usuario> result;
        try{
            result = service.asignarUsuario(usuario,cursoId);
        }catch (FeignException.FeignClientException e){
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/crear/usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@PathVariable Long cursoId, @RequestBody Usuario usuario){
        Optional<Usuario> result;
        try{
            result = service.crearUsuario(usuario,cursoId);
        }catch (FeignException.FeignClientException e){
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/eliminar/usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long cursoId, @RequestBody Usuario usuario){
        Optional<Usuario> result;
        try{
            result = service.eliminarUsuario(usuario,cursoId);
        }catch (FeignException.FeignClientException e){
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        if (result.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
