package com.example.VitaLife10.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "titulos")
@Data
public class Titulo {

    //Se crea la columna id en en la tabla de titulos y se autoincrementa segun los usuarios que se ingresen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Se crea la columna nombre_titulo
    @Column(name = "nombre_titulo")
    private String nombre;

    //Se crea la columna costo
    @Column(name = "costo")
    private int costo;

    //Se crea la columna tienda
    @Column(name = "tienda")
    private boolean tienda;

    //Se crea la relacion de muchos a muchos y una relacion inversa
    @ManyToMany(mappedBy = "titulos")
    private List<Usuario> usuarios = new ArrayList<>();


    // Constructores, getters y setters
    public Titulo() {
    }

    public Titulo(String nombre, int costo,  boolean tienda, Usuario usuario) {
        this.nombre = nombre;
        this.costo = costo;
        this.tienda = tienda;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public boolean isTienda() {
        return tienda;
    }

    public void setTienda(boolean tienda) {
        this.tienda = tienda;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Titulo titulo = (Titulo) o;
        return costo == titulo.costo  && tienda == titulo.tienda && Objects.equals(id, titulo.id) && Objects.equals(nombre, titulo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, costo, tienda);
    }
}
