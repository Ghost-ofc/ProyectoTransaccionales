package com.example.VitaLife10.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habitos_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitousuario")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "grupo")
    private boolean grupo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


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

    public boolean isGrupo() {
        return grupo;
    }

    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

