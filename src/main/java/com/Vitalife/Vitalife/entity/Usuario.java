package com.Vitalife.Vitalife.entity;


import com.Vitalife.Vitalife.DTO.HabitosDTO;
import com.Vitalife.Vitalife.DTO.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "usuario")
@Data
@JsonPropertyOrder({"id_usuario", "usuario", "contrasena", "correo", "puntos", "usuariohabi"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    @JsonProperty("id_usuario")
    private Long id_usuario;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "correo")
    private String correo;
    @Column(name = "puntos")
    private Integer puntos;

    @OneToMany(mappedBy = "usuariohabi", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto mapea la relación con la entidad Habito
    @JsonIgnoreProperties("usuariohabi")
    private List<Habitos> usuariohabi;

    public Usuario(Long id_usuario, String usuario, String contrasena, String correo, Integer puntos, List<Habitos> usuariohabi) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.puntos = puntos;
        this.usuariohabi = usuariohabi;
    }


    public Usuario() {

    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public List<Habitos> getUsuariohabi() {
        return usuariohabi;
    }

    public void setUsuariohabi(List<Habitos> usuariohabi) {
        this.usuariohabi = usuariohabi;
    }


}
