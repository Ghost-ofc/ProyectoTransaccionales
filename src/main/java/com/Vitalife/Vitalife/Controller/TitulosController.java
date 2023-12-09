package com.Vitalife.Vitalife.Controller;

import com.Vitalife.Vitalife.Service.TitulosService;
import com.Vitalife.Vitalife.entity.Titulos;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/titulos")
public class TitulosController {

    private final TitulosService titulosService;

    public TitulosController(TitulosService titulosService){
        this.titulosService = titulosService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarTitulo(@RequestBody Titulos titulos){
        try {
            Titulos agg = titulosService.agregarTitulo(titulos);
            return new ResponseEntity<>(agg, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/vertitulos")
    public ResponseEntity<?> verTitulos(){
        try {
            List<Titulos> vert = titulosService.verTitulos();
            return new ResponseEntity<>(vert, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
