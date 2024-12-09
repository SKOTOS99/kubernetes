package org.cursos.ms.app.cursos.ms.validate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateErrors {


    public ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo " +err.getField() + " " + err.getDefaultMessage());
        } );
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
