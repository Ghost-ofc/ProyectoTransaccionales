package com.Vitalife.Vitalife.DTO;

import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Marcaje;

import java.util.ArrayList;
import java.util.List;

public class HabitosDTO {
    private Long id_habito;
    private String nombreHabito;
    private Boolean completadoHabito;
    private Integer puntosrecompensahabito;
    private String tipohabito;
    private UsuarioDTO usuariohabi;
    private TituloDTO titulo;

    private MarcajeDTO marcaje;

    public HabitosDTO(){

    }

    public HabitosDTO(Long id_habito, String nombreHabito, Boolean completadoHabito, Integer puntosrecompensahabito, String tipohabito, UsuarioDTO usuariohabi, TituloDTO titulo, MarcajeDTO marcaje) {
        this.id_habito = id_habito;
        this.nombreHabito = nombreHabito;
        this.completadoHabito = completadoHabito;
        this.puntosrecompensahabito = puntosrecompensahabito;
        this.tipohabito = tipohabito;
        this.usuariohabi = usuariohabi;
        this.titulo = titulo;
        this.marcaje = marcaje;
    }


    public Long getId_habito() {
        return id_habito;
    }

    public void setId_habito(Long id_habito) {
        this.id_habito = id_habito;
    }

    public String getNombreHabito() {
        return nombreHabito;
    }

    public void setNombreHabito(String nombreHabito) {
        this.nombreHabito = nombreHabito;
    }

    public Boolean getCompletadoHabito() {
        return completadoHabito;
    }

    public void setCompletadoHabito(Boolean completadoHabito) {
        this.completadoHabito = completadoHabito;
    }

    public Integer getPuntosrecompensahabito() {
        return puntosrecompensahabito;
    }

    public void setPuntosrecompensahabito(Integer puntosrecompensahabito) {
        this.puntosrecompensahabito = puntosrecompensahabito;
    }

    public String getTipohabito() {
        return tipohabito;
    }

    public void setTipohabito(String tipohabito) {
        this.tipohabito = tipohabito;
    }

    public UsuarioDTO getUsuariohabi() {
        return usuariohabi;
    }

    public void setUsuariohabi(UsuarioDTO usuariohabi) {
        this.usuariohabi = usuariohabi;
    }

    public TituloDTO getTitulo() {
        return titulo;
    }

    public void setTitulo(TituloDTO titulo) {
        this.titulo = titulo;
    }

    public MarcajeDTO getMarcaje() {
        return marcaje;
    }

    public void setMarcaje(MarcajeDTO marcaje) {
        this.marcaje = marcaje;
    }

    public HabitosDTO toDTO(Habitos habito) {
        HabitosDTO dto = new HabitosDTO();
        dto.setId_habito(habito.getId_habito());
        dto.setNombreHabito(habito.getNombreHabito());
        dto.setPuntosrecompensahabito(habito.getPuntosrecompensahabito());
        dto.setTipohabito(habito.getTipohabito());
        if (habito.getMarcaje() != null && !habito.getMarcaje().isEmpty()) {
            List<MarcajeDTO> marcajeDTOList = new ArrayList<>();
            for (Marcaje marcaje : habito.getMarcaje()) {
                MarcajeDTO marcajeDTO = new MarcajeDTO();
                marcajeDTO.setId_marcaje(marcaje.getId_marcaje());
                marcajeDTO.setEstado(marcaje.getEstado());
                marcajeDTOList.add(marcajeDTO);
            }
            dto.setMarcaje(marcajeDTOList.get(0));
        }


        return dto;
    }
}
