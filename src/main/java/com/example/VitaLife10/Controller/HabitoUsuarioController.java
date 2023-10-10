package com.example.VitaLife10.Controller;


import com.example.VitaLife10.Metodos.HabitoUsuarioRequest;
import com.example.VitaLife10.Service.HabitoUsuarioService;
import com.example.VitaLife10.entity.HabitoUsuario;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("habitos")
public class HabitoUsuarioController {

    private final HabitoUsuarioService habitoUsuarioService;

    @Autowired
    public HabitoUsuarioController(HabitoUsuarioService habitoUsuarioService){
        this.habitoUsuarioService = habitoUsuarioService;
    }

    @GetMapping("/vertodos")
    public ResponseEntity<List<HabitoUsuario>> verTodosHabitos(){
        List<HabitoUsuario> habitos = habitoUsuarioService.verTodosHabitos();
        return new ResponseEntity<>(habitos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<HabitoUsuario> crearHabitoUsuario(@RequestBody HabitoUsuarioRequest request) {
        try {
            HabitoUsuario nuevoHabito = habitoUsuarioService.crearHabitoUsuario(request);
            return new ResponseEntity<>(nuevoHabito, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/grupos")
    public ResponseEntity<List<HabitoUsuario>> verificarHabitoGrupo(){
        List<HabitoUsuario> grupostodos = habitoUsuarioService.verificarHabitoGrupo(true);
        return new ResponseEntity<>(grupostodos, HttpStatus.OK);
    }

    @PostMapping("/sumarpuntos")
    public ResponseEntity<Object> sumarPuntosHabitosUsuario(@RequestBody Map<String, Object> requestMap) {
        try {

            String nombreUsuario = (String) requestMap.get("nombreUsuario");
            String nombreHabito = (String) requestMap.get("nombreHabito");

            try {
                Usuario usuario = habitoUsuarioService.sumarPuntosHabitosUsuario(nombreUsuario, nombreHabito);
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }catch (IllegalStateException e){
                String errorMessage = e.getMessage();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
