package org.cursos.ms.app.cursos.ms.controller;


import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import org.cursos.ms.app.cursos.ms.model.entity.CursoEntity;
import org.cursos.ms.app.cursos.ms.service.CursoService;
import org.cursos.ms.app.cursos.ms.validate.ValidateErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CursosController {

    private final CursoService service;

    private final ValidateErrors errorsValid;

     public CursosController(CursoService service, ValidateErrors errorsValid) {
         this.service = service;
         this.errorsValid = errorsValid;
    }

    @GetMapping(value = "/listar/cursos")
    public ResponseEntity<List<CursoEntity>> getCursos(){

         List<CursoEntity> cursos = service.listar();
         return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping(value = "/listar/cursos/{id}")
    public ResponseEntity<CursoEntity> getPorId(@PathVariable Long id){
        Optional<CursoEntity> curso = service.porId(id);
        return curso.map(c -> new ResponseEntity<>(c,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar/curso")
    public ResponseEntity<?> guardarCurso(@Valid @RequestBody CursoEntity curso, BindingResult result){
        if(result.hasErrors()){
            return errorsValid.getMapResponseEntity(result);
        }

         return new ResponseEntity<>(service.guardarCurso(curso), HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/curso/{id}")
    public ResponseEntity<String> eliminarCurso(@PathVariable Long id){
        Optional<CursoEntity> curso = service.porId(id);
        if (curso.isPresent()){
            service.eliminarCurso(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No existe el registro", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/actualizar/curso/{id}")
    public ResponseEntity<?> actualizarCurso( @Valid @PathVariable Long id,
                                                        @RequestBody CursoEntity curso, BindingResult result){
        if(result.hasErrors()){
            return errorsValid.getMapResponseEntity(result);
        }
         Optional<CursoEntity> cursos = service.actualizarCurso(id,curso);
        return cursos.map(cur -> new ResponseEntity<>(cur, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
