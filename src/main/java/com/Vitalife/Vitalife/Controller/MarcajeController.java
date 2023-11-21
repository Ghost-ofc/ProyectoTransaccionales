package com.Vitalife.Vitalife.Controller;

import com.Vitalife.Vitalife.DTO.MarcajeDTO;
import com.Vitalife.Vitalife.Service.MarcajeService;
import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Marcaje;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcaje")
public class MarcajeController {
    private final MarcajeService marcajeService;



    public MarcajeController(MarcajeService marcajeService){
        this.marcajeService = marcajeService;
    }

    @PostMapping("/completarhabito")
    public ResponseEntity<?> completarHabito(@RequestParam Long idMarcaje){
        try {
            Marcaje habitoCompletado = marcajeService.completarMarcaje(idMarcaje);
            return new ResponseEntity<>(habitoCompletado, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/vermarcajes")
    public ResponseEntity<?> verMarcajes(){
        try {
            List<MarcajeDTO> marc = marcajeService.verTodosMarcaje();
            return new ResponseEntity<>(marc, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
