package org.montes.springcloud.micro.cursos.microcursos.services;

import org.montes.springcloud.micro.cursos.microcursos.entity.Curso;
import org.montes.springcloud.micro.cursos.microcursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService{

    @Autowired CursoRepository cursoRepository;



    @Override
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}
