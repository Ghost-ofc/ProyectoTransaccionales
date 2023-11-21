package com.Vitalife.Vitalife.Service;

import com.Vitalife.Vitalife.DTO.MarcajeDTO;
import com.Vitalife.Vitalife.Repository.MarcajeRepository;
import com.Vitalife.Vitalife.Repository.TitulosRepository;
import com.Vitalife.Vitalife.Repository.UsuarioRepository;
import com.Vitalife.Vitalife.entity.Marcaje;
import com.Vitalife.Vitalife.entity.Titulos;
import com.Vitalife.Vitalife.entity.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MarcajeService {

    private final MarcajeRepository marcajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final TitulosRepository titulosRepository;

    public MarcajeService(MarcajeRepository marcajeRepository, UsuarioRepository usuarioRepository, TitulosRepository titulosRepository) {
        this.marcajeRepository = marcajeRepository;
        this.usuarioRepository = usuarioRepository;
        this.titulosRepository = titulosRepository;
    }

    public Marcaje completarMarcaje(Long idMarcaje) {
        // Buscar el marcaje por su ID
        Optional<Marcaje> marcajeOptional = marcajeRepository.findById(idMarcaje);

        if (marcajeOptional.isPresent()) {
            Marcaje marcaje = marcajeOptional.get();

            // Verificar si el hábito asociado al marcaje ya está completado
            if (!marcaje.isCompletado()) {
                // Marcar el hábito asociado al marcaje como completado
                marcaje.setEstado(true);

                // Obtener la información del usuario asociado al hábito
                Usuario usuario = marcaje.getUsuariomar();

                if (usuario != null) {
                    // Agregar los puntos del hábito al usuario
                    usuario.setPuntos(usuario.getPuntos() + marcaje.getHabitosmar().getPuntosrecompensahabito());

                    // Actualizar el usuario en la base de datos
                    usuarioRepository.save(usuario);

                    // Obtener el título asociado al hábito
                    Titulos titulo = marcaje.getHabitosmar().getTitulo();

                    if (titulo != null) {
                        // Actualizar el progreso del título
                        titulo.setProgresotitulo(titulo.getProgresotitulo() + marcaje.getHabitosmar().getPuntosrecompensahabito());

                        // Actualizar el título en la base de datos
                        titulosRepository.save(titulo);
                    } else {
                        throw new NullPointerException("El hábito no contiene un título asociado.");
                    }
                } else {
                    throw new NullPointerException("El hábito no contiene un usuario asociado.");
                }

                // Guardar el marcaje actualizado en la base de datos
                return marcajeRepository.save(marcaje);
            } else {
                throw new EntityNotFoundException("El hábito asociado al marcaje ya está completado.");
            }
        } else {
            throw new NoResultException("No se encontró el marcaje por ID: " + idMarcaje);
        }
    }

    public List<MarcajeDTO> verTodosMarcaje() {
        List<Marcaje> marc = marcajeRepository.findAll();

        // Crear una lista para almacenar los DTOs
        List<MarcajeDTO> marcajeDTOList = new ArrayList<>();

        // Iterar sobre la lista de Marcaje y convertir cada elemento a MarcajeDTO
        for (Marcaje marcaje : marc) {
            MarcajeDTO marcajeDTO = new MarcajeDTO().convertToDTO(marcaje);
            marcajeDTOList.add(marcajeDTO);
        }

        return marcajeDTOList;
    }

}
