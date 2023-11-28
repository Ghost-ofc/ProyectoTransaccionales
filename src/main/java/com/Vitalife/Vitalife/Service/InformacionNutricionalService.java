package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.Repository.InformacionNutricionalRepository;
import com.Vitalife.Vitalife.entity.InformacionNutricional;
import org.springframework.stereotype.Service;

@Service
public class InformacionNutricionalService {

    private final InformacionNutricionalRepository informacionNutricionalRepository;

    public InformacionNutricionalService(InformacionNutricionalRepository informacionNutricionalRepository) {
        this.informacionNutricionalRepository = informacionNutricionalRepository;
    }

    public String agregarInformacion(InformacionNutricional informacionNutricional){
        informacionNutricionalRepository.save(informacionNutricional);
        return "Informacion Nutricional agregada";
    }
}
