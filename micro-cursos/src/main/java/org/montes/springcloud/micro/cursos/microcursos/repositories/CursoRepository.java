package org.montes.springcloud.micro.cursos.microcursos.repositories;

import org.montes.springcloud.micro.cursos.microcursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {


}
