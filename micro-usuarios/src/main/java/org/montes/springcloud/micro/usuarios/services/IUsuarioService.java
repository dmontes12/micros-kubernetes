package org.montes.springcloud.micro.usuarios.services;

import org.montes.springcloud.micro.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void elimiar(Long id);
    Optional<Usuario> porEmail(String email);




}
