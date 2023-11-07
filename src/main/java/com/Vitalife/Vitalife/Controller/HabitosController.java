package com.Vitalife.Vitalife.Controller;

import com.Vitalife.Vitalife.DTO.HabitosDTO;
import com.Vitalife.Vitalife.Service.HabitosService;
import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Titulos;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitos")
public class HabitosController {

    private final HabitosService habitosService;

    public HabitosController(HabitosService habitosService){
        this.habitosService = habitosService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregaarHabito(@RequestBody Habitos habitos){
        try {
            Habitos agg = habitosService.agregarHabito(habitos);
            return new  ResponseEntity<>(agg, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verhabito/{id}")
    public ResponseEntity<?> verHabitos(@PathVariable Long id){
        try {
            HabitosDTO ver = habitosService.verHabitos(id);
            return new ResponseEntity<>(ver, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vertodoshabitos")
    public ResponseEntity<?> verTodosHabitos(){
        List<Habitos> vert = habitosService.vetTodosHabitos();
        return new ResponseEntity<>(vert, HttpStatus.OK);
    }

    @GetMapping("/verhabitospersonalizados")
    public ResponseEntity<?> verHabitosPersonalizados(){
        try {
            List<Habitos> perso = habitosService.verHabitosPersonalizados();
            return new ResponseEntity<>(perso, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/verhabitosistema")
    public ResponseEntity<?> verHabitosSistema(){
        try {
            List<Habitos> siste = habitosService.verHabitosSistema();
            return new ResponseEntity<>(siste, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/verhabitosgrupo")
    public ResponseEntity<?> verHabitosGrupo(){
        try {
            List<Habitos> grup = habitosService.verHabitosGrupo();
            return new ResponseEntity<>(grup, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/completarhabito")
    public ResponseEntity<?> completarHabito(@RequestParam Long idHabito){
        try {
            Habitos habitoCompletado = habitosService.completarHabitos(idHabito);
            return new ResponseEntity<>(habitoCompletado, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
