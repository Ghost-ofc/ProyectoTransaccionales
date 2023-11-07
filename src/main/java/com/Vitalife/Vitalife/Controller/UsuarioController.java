package com.Vitalife.Vitalife.Controller;

import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.Vitalife.Vitalife.Service.UsuarioService;
import com.Vitalife.Vitalife.entity.Usuario;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){
        try{
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestParam String usuario, String contrasena){
        try {
            Usuario login = usuarioService.iniciarSesion(usuario, contrasena);
            return new ResponseEntity<>(login, HttpStatus.OK);
        }catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/modificarcontrasena")
    public ResponseEntity<?> modificarContrasena(@RequestParam Long id, @RequestParam String contrasena){
        try{
            Usuario contra = usuarioService.modificarContrasena(id, contrasena);
            return new ResponseEntity<>(contra, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verperfil/{id}")
    public ResponseEntity<?> verPerfil(@PathVariable Long id){
        try {
            UsuarioDTO perfil = usuarioService.verPerfil(id);
            return new ResponseEntity<>(perfil, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/verpuntos/{id}")
    public ResponseEntity<?> verPuntos(@PathVariable Long id){
        try{
            Usuario punto = usuarioService.verPuntos(id);
            return new ResponseEntity<>(punto.getPuntos(), HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
