package org.montes.springcloud.micro.cursos.microcursos.controllers;


import jakarta.validation.Valid;
import org.montes.springcloud.micro.cursos.microcursos.entity.Curso;
import org.montes.springcloud.micro.cursos.microcursos.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar(){
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> porId(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.porId(id);

        if(curso.isPresent()){
            return ResponseEntity.ok(curso);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result){
        Map<String,String> errores = new HashMap<>();
        if(result.hasFieldErrors()){
            return validarCursoCampos(result, errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }



    @PutMapping("{}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> optionalCurso = cursoService.porId(id);

        if(optionalCurso.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> optionalCurso = cursoService.porId(id);

        if(optionalCurso.isPresent()){
            cursoService.eliminar(id);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Map<String, String>> validarCursoCampos(BindingResult result, Map<String, String> errores) {
        result.getFieldErrors().forEach(err-> errores.put(err.getField(),"Error: ".concat(err.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errores);
    }

}
