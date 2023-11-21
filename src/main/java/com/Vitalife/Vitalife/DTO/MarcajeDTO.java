package com.Vitalife.Vitalife.DTO;

import com.Vitalife.Vitalife.entity.Marcaje;

public class MarcajeDTO {

    private Long id_marcaje;
    private Boolean estado;
    private HabitosDTO habitosmar;
    private TituloDTO titulos;
    private UsuarioDTO usuariomar;

    public MarcajeDTO() {
    }

    public Long getId_marcaje() {
        return id_marcaje;
    }

    public void setId_marcaje(Long id_marcaje) {
        this.id_marcaje = id_marcaje;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public HabitosDTO getHabitosmar() {
        return habitosmar;
    }

    public void setHabitosmar(HabitosDTO habitosmar) {
        this.habitosmar = habitosmar;
    }

    public TituloDTO getTitulos() {
        return titulos;
    }

    public void setTitulos(TituloDTO titulos) {
        this.titulos = titulos;
    }

    public UsuarioDTO getUsuariomar() {
        return usuariomar;
    }

    public void setUsuariomar(UsuarioDTO usuariomar) {
        this.usuariomar = usuariomar;
    }


    public MarcajeDTO convertToDTO(Marcaje marcaje) {
        MarcajeDTO marcajeDTO = new MarcajeDTO();
        marcajeDTO.setId_marcaje(marcaje.getId_marcaje());
        marcajeDTO.setEstado(marcaje.getEstado());
        marcajeDTO.setHabitosmar(marcajeDTO.getHabitosmar());
        marcajeDTO.setUsuariomar(marcajeDTO.getUsuariomar());
        // Mapear otros campos según sea necesario

        return marcajeDTO;
    }
}
