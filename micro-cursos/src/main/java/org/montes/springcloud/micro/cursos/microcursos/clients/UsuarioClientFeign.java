package org.montes.springcloud.micro.cursos.microcursos.clients;

import org.montes.springcloud.micro.cursos.microcursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="micro-usuarios",url="micro-usuarios:8001")
public interface UsuarioClientFeign {
    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuario);

    @GetMapping("/usuarios-por-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long>ids);
}
