package com.Vitalife.Vitalife.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "informacionnutricional")
@Data
public class InformacionNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacion")
    private Long id_informacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne(mappedBy = "tituloinfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto mapea la relación con la entidad Titulos
    private Titulos tituloinfo;

    @OneToMany(mappedBy = "marcajeinfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto mapea la relación con la entidad Marcaje
    private List<Marcaje> marcajeinfo;

    @OneToMany(mappedBy = "usuarioinfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto mapea la relación con la entidad Usuario
    private List<Usuario> usuarioinfo;

    public InformacionNutricional() {
    }

    public InformacionNutricional(Long id_informacion, String nombre, String descripcion, Titulos tituloinfo, List<Marcaje> marcajeinfo, List<Usuario> usuarioinfo) {
        this.id_informacion = id_informacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tituloinfo = tituloinfo;
        this.marcajeinfo = marcajeinfo;
        this.usuarioinfo = usuarioinfo;
    }

    public Long getId_informacion() {
        return id_informacion;
    }

    public void setId_informacion(Long id_informacion) {
        this.id_informacion = id_informacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Titulos getTituloinfo() {
        return tituloinfo;
    }

    public void setTituloinfo(Titulos tituloinfo) {
        this.tituloinfo = tituloinfo;
    }

    public List<Marcaje> getMarcajeinfo() {
        return marcajeinfo;
    }

    public void setMarcajeinfo(List<Marcaje> marcajeinfo) {
        this.marcajeinfo = marcajeinfo;
    }

    public List<Usuario> getUsuarioinfo() {
        return usuarioinfo;
    }

    public void setUsuarioinfo(List<Usuario> usuarioinfo) {
        this.usuarioinfo = usuarioinfo;
    }
}
