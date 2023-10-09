package com.example.VitaLife10.Controller;


import com.example.VitaLife10.Metodos.HabitoUsuarioRequest;
import com.example.VitaLife10.Service.HabitoUsuarioService;
import com.example.VitaLife10.entity.HabitoUsuario;
import com.example.VitaLife10.entity.Titulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
