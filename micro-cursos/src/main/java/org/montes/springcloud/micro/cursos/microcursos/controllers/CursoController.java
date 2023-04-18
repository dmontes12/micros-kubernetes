package org.montes.springcloud.micro.cursos.microcursos.controllers;


import org.montes.springcloud.micro.cursos.microcursos.entity.Curso;
import org.montes.springcloud.micro.cursos.microcursos.services.ICursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CursoController {

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
    public ResponseEntity<?> crear(@RequestBody Curso curso){
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

    @DeleteMapping
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> optionalCurso = cursoService.porId(id);

        if(optionalCurso.isPresent()){
            cursoService.eliminar(id);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
