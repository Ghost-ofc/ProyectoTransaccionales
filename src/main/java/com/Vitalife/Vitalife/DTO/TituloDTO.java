package com.Vitalife.Vitalife.DTO;

import com.Vitalife.Vitalife.entity.Habitos;

import java.util.List;

public class TituloDTO {

    private Long id_titulo;
    private String nombretitulo;
    private Integer puntosrequeridostitulo;
    private String niveltitulo;
    private Integer progresotitulo;
    private Boolean obtenidotitulo;
    private List<HabitosDTO> usuariohabi;

    public TituloDTO(){

    }

    public TituloDTO(Long id_titulo, String nombretitulo, Integer puntosrequeridostitulo, String niveltitulo, Integer progresotitulo, Boolean obtenidotitulo, List<HabitosDTO> usuariohabi) {
        this.id_titulo = id_titulo;
        this.nombretitulo = nombretitulo;
        this.puntosrequeridostitulo = puntosrequeridostitulo;
        this.niveltitulo = niveltitulo;
        this.progresotitulo = progresotitulo;
        this.obtenidotitulo = obtenidotitulo;
        this.usuariohabi = usuariohabi;
    }

    public Long getId_titulo() {
        return id_titulo;
    }

    public void setId_titulo(Long id_titulo) {
        this.id_titulo = id_titulo;
    }

    public String getNombretitulo() {
        return nombretitulo;
    }

    public void setNombretitulo(String nombretitulo) {
        this.nombretitulo = nombretitulo;
    }

    public Integer getPuntosrequeridostitulo() {
        return puntosrequeridostitulo;
    }

    public void setPuntosrequeridostitulo(Integer puntosrequeridostitulo) {
        this.puntosrequeridostitulo = puntosrequeridostitulo;
    }

    public String getNiveltitulo() {
        return niveltitulo;
    }

    public void setNiveltitulo(String niveltitulo) {
        this.niveltitulo = niveltitulo;
    }

    public Integer getProgresotitulo() {
        return progresotitulo;
    }

    public void setProgresotitulo(Integer progresotitulo) {
        this.progresotitulo = progresotitulo;
    }

    public Boolean getObtenidotitulo() {
        return obtenidotitulo;
    }

    public void setObtenidotitulo(Boolean obtenidotitulo) {
        this.obtenidotitulo = obtenidotitulo;
    }

    public List<HabitosDTO> getUsuariohabi() {
        return usuariohabi;
    }

    public void setUsuariohabi(List<HabitosDTO> usuariohabi) {
        this.usuariohabi = usuariohabi;
    }


}
