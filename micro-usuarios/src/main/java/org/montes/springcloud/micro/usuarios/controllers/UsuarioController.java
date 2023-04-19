package org.montes.springcloud.micro.usuarios.controllers;


import jakarta.validation.Valid;
import org.montes.springcloud.micro.usuarios.models.entity.Usuario;
import org.montes.springcloud.micro.usuarios.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;


    @GetMapping("/listar")
    public List<Usuario> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result){

        if(result.hasErrors()){
            return validarCamposUsuario(result);
        }
        if(!usuario.getEmail().isEmpty() && service.porEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Ya existe un usuario con ese email"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@Valid @RequestBody Usuario usuario,BindingResult result, @PathVariable Long id){


        if(result.hasErrors()){
            return validarCamposUsuario(result);
        }
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            if(!usuario.getEmail().isEmpty() && !usuario.getEmail().equalsIgnoreCase(usuarioOptional.get().getEmail()) && service.porEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","Ya existe un usuario con ese email"));
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.guardar(usuario));
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            service.elimiar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }


    private ResponseEntity<Map<String, String>> validarCamposUsuario(BindingResult result) {
        Map<String,String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(),"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }




}
