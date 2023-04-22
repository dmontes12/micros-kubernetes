package org.montes.springcloud.micro.cursos.microcursos.services;

import org.montes.springcloud.micro.cursos.microcursos.clients.UsuarioClientFeign;
import org.montes.springcloud.micro.cursos.microcursos.models.Usuario;
import org.montes.springcloud.micro.cursos.microcursos.models.entity.Curso;
import org.montes.springcloud.micro.cursos.microcursos.models.entity.CursoUsuario;
import org.montes.springcloud.micro.cursos.microcursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService{

    @Autowired CursoRepository cursoRepository;

    @Autowired
    private UsuarioClientFeign clientFeign;



    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> optionalCurso = cursoRepository.findById(cursoId);
        if(optionalCurso.isPresent()){
            Usuario usuarioFeign = clientFeign.detalle(usuario.getId());

            Curso curso = optionalCurso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioFeign.getId());
            curso.addCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);
            return Optional.of(usuarioFeign);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> optionalCurso = cursoRepository.findById(cursoId);
        if(optionalCurso.isPresent()){
            Usuario usuarioFeign = clientFeign.crear(usuario);

            Curso curso = optionalCurso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioFeign.getId());
            curso.addCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);
            return Optional.of(usuarioFeign);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuarioDeCurso(Usuario usuario, Long cursoId) {
        Optional<Curso> optionalCurso = cursoRepository.findById(cursoId);
        if(optionalCurso.isPresent()){
            Usuario usuarioFeign = clientFeign.detalle(usuario.getId());

            Curso curso = optionalCurso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioFeign.getId());
            curso.removeCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);
            return Optional.of(usuarioFeign);
        }
        return Optional.empty();
    }
}
