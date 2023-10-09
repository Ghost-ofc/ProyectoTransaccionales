package com.example.VitaLife10.Controller;

import com.example.VitaLife10.Service.HabitoSistemaService;
import com.example.VitaLife10.entity.HabitoSistema;
import com.example.VitaLife10.entity.HabitoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("habitosSistema")
public class HabitoSistemaController {

    private final HabitoSistemaService habitoSistemaService;

    @Autowired
    public HabitoSistemaController(HabitoSistemaService habitoSistemaService){
        this.habitoSistemaService = habitoSistemaService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<HabitoSistema> agregarHabitoSistema(@RequestBody HabitoSistema habitoSistema){
        HabitoSistema nuevoHabito = habitoSistemaService.agregarHabitoSistema(habitoSistema);
        return new ResponseEntity<>(nuevoHabito, HttpStatus.CREATED);
    }

    @GetMapping("vertodos")
    public ResponseEntity<List<HabitoSistema>> verTodosHabitoSistema(){
        List<HabitoSistema> habitosSis = habitoSistemaService.verTodosHabitoSistema();
        return new ResponseEntity<>(habitosSis, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> eliminarHabitoPorId(@PathVariable Long id){
        Boolean deleteid = habitoSistemaService.eliminarHabitoPorId(id);
        if(deleteid){
            return new ResponseEntity<>(deleteid, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
