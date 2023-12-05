package com.Vitalife.Vitalife.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "titulos")
@Data

public class Titulos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titulo")
    private Long id_titulo;
    @Column(name = "nombre")
    private String nombretitulo;
    @Column(name = "puntosrequeridos")
    private Integer puntosrequeridostitulo;
    @Column(name = "nivel")
    private String niveltitulo;
    @Column(name = "progreso")
    private Integer progresotitulo;
    @Column(name = "obtenido")
    private Boolean obtenidotitulo;
    @OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto mapea la relación con la entidad Habito
    @JsonIgnoreProperties("titulo")
    private List<Habitos> habitos;

    @OneToMany(mappedBy = "titulos", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto mapea la relación con la entidad Marcaje
    @JsonIgnoreProperties("titulos")
    private List<Marcaje> marcaje;

    @OneToOne
    @JoinColumn(name = "informacionnutricional_id_informacion")
    private InformacionNutricional tituloinfo;

    public Titulos(){}

    public Titulos(Long id_titulo, String nombretitulo, Integer puntosrequeridostitulo, String niveltitulo, Integer progresotitulo, Boolean obtenidotitulo, List<Habitos> habitos, List<Marcaje> marcaje, InformacionNutricional tituloinfo) {
        this.id_titulo = id_titulo;
        this.nombretitulo = nombretitulo;
        this.puntosrequeridostitulo = puntosrequeridostitulo;
        this.niveltitulo = niveltitulo;
        this.progresotitulo = progresotitulo;
        this.obtenidotitulo = obtenidotitulo;
        this.habitos = habitos;
        this.marcaje = marcaje;
        this.tituloinfo = tituloinfo;
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

    public List<Habitos> getHabitos() {
        return habitos;
    }

    public void setHabitos(List<Habitos> habitos) {
        this.habitos = habitos;
    }

    public List<Marcaje> getMarcaje() {
        return marcaje;
    }

    public void setMarcaje(List<Marcaje> marcaje) {
        this.marcaje = marcaje;
    }

    public InformacionNutricional getTitulosinfo() {
        return tituloinfo;
    }

    public void setTitulosinfo(InformacionNutricional titulosinfo) {
        this.tituloinfo = titulosinfo;
    }
}
