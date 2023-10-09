package com.example.VitaLife10.Controller;

import com.example.VitaLife10.Service.UsuarioService;
import com.example.VitaLife10.Metodos.ApiResponse;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Se registra al usuario mediante metodo POST con un JSON
    @PostMapping("/registrar/agregar")
    public ResponseEntity<ApiResponse> agregarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);

            String mensaje = "Usuario agregado con el nombre de usuario: " + nuevoUsuario.getNombreUsuario();
            ApiResponse respuesta = new ApiResponse(mensaje, nuevoUsuario);

            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Manejar la excepción y devolver una respuesta JSON adecuada
            String mensaje = e.getMessage();
            ApiResponse respuesta = new ApiResponse(mensaje, null);

            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    //Se busca al usuario segun su id como parametro en un metodo GET
    @GetMapping("/registrar/{nombreUsuario}")
    public ResponseEntity<Usuario> obtenerUsuarioPorUsername(@PathVariable String nombreUsuario) {
        Usuario usuario = usuarioService.obtenerUsuarioPorUsername(nombreUsuario);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Se busca a todos los usuarios mediante un metodo GET
    @GetMapping("/vertodos")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    //Se elimina a los usuarios segun el id como parametro en un metodo DELETE
    @DeleteMapping("/registrar/delete/{id}")
    public ResponseEntity<ApiResponse> eliminarUsuario(@PathVariable Long id) {
        Usuario usuarioEliminado = usuarioService.obtenerUsuarioPorId(id); // Obtiene los datos del usuario a eliminar
        boolean eliminado = usuarioService.eliminarUsuarioPorId(id); // Elimina el usuario


        if (eliminado) {
            String mensaje = "Usuario eliminado con éxito";
            ApiResponse respuesta = new ApiResponse(mensaje, usuarioEliminado); // Incluso si es null, se incluirá en la respuesta
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            String mensaje = "No se encontró el usuario: " + id;
            ApiResponse respuesta = new ApiResponse(mensaje, null); // Puedes enviar un objeto ApiResponse con usuarioEliminado como null
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/verPuntos/{nombreUsuario}")
    public ResponseEntity<ApiResponse> verPuntosPorUsername(@PathVariable String nombreUsuario){
        Usuario puntosUse = usuarioService.verPuntosPorUsername(nombreUsuario);

        if(puntosUse != null){
            String mensaje = "Puntos del usuario: " + puntosUse.getPuntos();
            ApiResponse respuesta = new ApiResponse(mensaje, null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}

