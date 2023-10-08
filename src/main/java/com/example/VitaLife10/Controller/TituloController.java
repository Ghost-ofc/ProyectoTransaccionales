package com.example.VitaLife10.Controller;


import com.example.VitaLife10.Service.TituloService;
import com.example.VitaLife10.entity.ApiResponse;
import com.example.VitaLife10.entity.Titulo;
import com.example.VitaLife10.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("titulos")
public class TituloController {

    private final TituloService tituloService;

    @Autowired
    public TituloController(TituloService tituloService){
        this.tituloService = tituloService;
    }

    //Se agregan los titulos mediante un JSON
    @PostMapping("/tienda/agregar")
    public ResponseEntity<Titulo> guardarTitulo(@RequestBody Titulo titulo) {
        Titulo nuevoTitulo = tituloService.guardarTitulo(titulo);
        return new ResponseEntity<>(titulo, HttpStatus.CREATED);
    }

    //Se obtienen todos los usuarios metiante un metodo get
    @GetMapping("/tienda")
    public ResponseEntity<List<Titulo>> obtenerTodosLosTitulos(){
        List<Titulo> titulos = tituloService.obtenerTodosLosTitulos();
        return new ResponseEntity<>(titulos, HttpStatus.OK);
    }

    //Se hace una consulta Get pasandole el parametro de la id a buscar
    @GetMapping("/tienda/consulta/{id}")
    public ResponseEntity<Titulo> obtenerTituloPorId(@PathVariable Long id){
        Titulo titulo = tituloService.obtenerTituloPorId(id);
        if (titulo != null) {
            return new ResponseEntity<>(titulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Se hace la consulta mediante un form-data y metodo post
    @PostMapping("/tienda/asignarTitulo")
    public ResponseEntity<String> asignarTituloAUsuarios(@RequestParam Long tituloId, @RequestParam List<Long> usuarioIds) {
        try {
            tituloService.asignarTituloAUsuarios(tituloId, usuarioIds);
            return ResponseEntity.ok("Título asignado a usuarios exitosamente.");
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: IdTitulo o IdUsuario no encontrado, por favor verificar");
        }

    }

}
