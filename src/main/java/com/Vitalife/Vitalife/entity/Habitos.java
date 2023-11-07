package com.Vitalife.Vitalife.entity;

import com.Vitalife.Vitalife.DTO.HabitosDTO;
import com.Vitalife.Vitalife.DTO.TituloDTO;
import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "habitos")
@Data
public class Habitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitos")

    private Long id_habito;
    @Column(name = "nombre")
    private String nombreHabito;
    @Column(name = "completado")
    private Boolean completadoHabito;
    @Column(name = "puntosrecompensa")
    private Integer puntosrecompensahabito;
    @Column(name = "tipo")
    private String tipohabito;

    @ManyToOne
    @JoinColumn(name = "titulos_id_titulo") // Esto mapea la relación con la entidad Titulos
    private Titulos titulo;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario") // Cambiado a "usuario_id_usuario"
    @JsonIgnore
    private Usuario usuariohabi;


    public Habitos(Long id_habito, String nombreHabito, Boolean completadoHabito, Integer puntosrecompensahabito, String tipohabito, Titulos titulo, Usuario usuariohabi) {
        this.id_habito = id_habito;
        this.nombreHabito = nombreHabito;
        this.completadoHabito = completadoHabito;
        this.puntosrecompensahabito = puntosrecompensahabito;
        this.tipohabito = tipohabito;
        this.titulo = titulo;
        this.usuariohabi = usuariohabi;
    }

    public Habitos() {

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

    public boolean isCompletadoHabito() {
        return completadoHabito;
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

    public void setTitulo(Titulos titulo) {
        this.titulo = titulo;
    }
    public Titulos getTitulo() {
        return titulo;
    }
    public Usuario getUsuariohabi() {
        return usuariohabi;
    }

    public void setUsuariohabi(Usuario usuariohabi) {
        this.usuariohabi = usuariohabi;
    }


}
