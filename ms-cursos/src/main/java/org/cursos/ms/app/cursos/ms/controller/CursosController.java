package org.cursos.ms.app.cursos.ms.controller;


import jakarta.annotation.PreDestroy;
import org.cursos.ms.app.cursos.ms.model.entity.CursoEntity;
import org.cursos.ms.app.cursos.ms.service.CursoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CursosController {

    private final CursoService service;

     public CursosController(CursoService service) {
         this.service = service;
    }

    @GetMapping(value = "/listar/cursos")
    public ResponseEntity<List<CursoEntity>> getCursos(){

         List<CursoEntity> cursos = service.listar();
         return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping(value = "/listar/cusos/{id}")
    public ResponseEntity<CursoEntity> getPorId(@PathVariable Long id){
        Optional<CursoEntity> curso = service.porId(id);
        return curso.map(c -> new ResponseEntity<>(c,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar/curso")
    public ResponseEntity<CursoEntity> guardarCurso(@RequestBody CursoEntity curso){
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
    public ResponseEntity<CursoEntity> actualizarCurso( @PathVariable Long id,
                                                        @RequestBody CursoEntity curso){
        Optional<CursoEntity> cursos = service.actualizarCurso(id,curso);
        return cursos.map(cur -> new ResponseEntity<>(cur, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
