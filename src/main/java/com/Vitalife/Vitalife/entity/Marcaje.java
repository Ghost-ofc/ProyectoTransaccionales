package com.Vitalife.Vitalife.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "marcaje")
@Data
public class Marcaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marcaje")
    @JsonProperty("id_marcaje")
    private Long id_marcaje;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "habitos_id_habitos") // Esto mapea la relación con la entidad Usuario
    private Habitos habitomarcaje;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario") // Esto mapea la relación con la entidad Usuario
    private Usuario usuariomar;

    @ManyToOne
    @JoinColumn(name = "titulos_id_titulo") // Esto mapea la relación con la entidad Titulos
    private Titulos titulos;


    public Marcaje() {
    }

    public Marcaje(Long id_marcaje, Boolean estado, Habitos habitomarcaje, Usuario usuariomar, Titulos titulos) {
        this.id_marcaje = id_marcaje;
        this.estado = estado;
        this.habitomarcaje = habitomarcaje;
        this.usuariomar = usuariomar;
        this.titulos = titulos;
    }

    public boolean isCompletado() {
        return estado;
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

    public Habitos getHabitosmar() {
        return habitomarcaje;
    }

    public void setHabitosmar(Habitos habitosmar) {
        this.habitomarcaje = habitosmar;
    }

    public Usuario getUsuariomar() {
        return usuariomar;
    }

    public void setUsuariomar(Usuario usuariomar) {
        this.usuariomar = usuariomar;
    }

    public Titulos getTitulos() {
        return titulos;
    }

    public void setTitulos(Titulos titulos) {
        this.titulos = titulos;
    }
}
