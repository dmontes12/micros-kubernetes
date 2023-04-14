package org.montes.springcloud.micro.usuarios.repositories;

import org.montes.springcloud.micro.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

}
