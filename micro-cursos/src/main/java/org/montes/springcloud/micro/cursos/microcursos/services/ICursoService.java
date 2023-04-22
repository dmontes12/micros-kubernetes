package org.montes.springcloud.micro.cursos.microcursos.services;

import org.montes.springcloud.micro.cursos.microcursos.models.Usuario;
import org.montes.springcloud.micro.cursos.microcursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);

    Optional<Usuario> eliminarUsuarioDeCurso(Usuario usuario, Long cursoId);
}
