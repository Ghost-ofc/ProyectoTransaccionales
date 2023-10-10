package com.example.VitaLife10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habitos_sistema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitosistema")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "realizado")
    private Boolean realizado;

    @Column(name = "puntoshabitossistema")
    private int puntoshabitossistema;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioH;


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

    public Boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public int getPuntoshabitossistema() {
        return puntoshabitossistema;
    }

    public void setPuntoshabitossistema(int puntoshabitossistema) {
        this.puntoshabitossistema = puntoshabitossistema;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public Usuario getUsuario() {
        return usuarioH;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioH = usuario;
    }


}
