package com.Vitalife.Vitalife.Controller;

import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.Vitalife.Vitalife.Repository.UsuarioRepository;
import com.Vitalife.Vitalife.Service.UsuarioService;
import com.Vitalife.Vitalife.entity.Usuario;
import com.Vitalife.Vitalife.mappers.LoginRequest;
import com.Vitalife.Vitalife.mappers.LoginResponse;
import com.Vitalife.Vitalife.util.EncryptionUtil;
import com.Vitalife.Vitalife.util.JwtTokenUtil;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){
        try{
            String nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        Optional<Usuario> user = usuarioRepository.findByUsuario(loginRequest.getUsername());
        System.out.println(user);
        if(user.isPresent()){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                return new LoginResponse(EncryptionUtil.encrypt(jwtTokenUtil.generateToken(user.get())));
            }catch (AuthenticationException e){

            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo y/o contraseña incorrecta");
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
            Integer punto = usuarioService.verPuntos(id);
            return new ResponseEntity<>(punto, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
