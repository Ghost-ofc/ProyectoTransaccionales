package com.Vitalife.Vitalife.Controller;

import com.Vitalife.Vitalife.Service.InformacionNutricionalService;
import com.Vitalife.Vitalife.entity.InformacionNutricional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/informacion")
public class InformacionNutricionalController {

    private final InformacionNutricionalService informacionNutricionalService;

    public InformacionNutricionalController(InformacionNutricionalService informacionNutricionalService) {
        this.informacionNutricionalService = informacionNutricionalService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarInformacionNutricional(InformacionNutricional informacionNutricional){
        try {
            String respuesta = informacionNutricionalService.agregarInformacion(informacionNutricional);
            return ResponseEntity.ok(respuesta);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
