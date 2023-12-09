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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "usuario")
@Data

public class Usuario implements UserDetails {

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
    private List<Habitos> habitos;

    @OneToMany(mappedBy = "usuariomar", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto mapea la relación con la entidad Marcaje
    @JsonIgnoreProperties("usuariomar")
    private List<Marcaje> marcaje;


    public Usuario(Long id_usuario, String usuario, String contrasena, String correo, Integer puntos, List<Habitos> habitos, List<Marcaje> marcaje) {
    @ManyToOne
    @JoinColumn(name = "informacionnutricional_id_informacion") // Esto mapea la relación con la entidad Informacion
    @JsonIgnoreProperties("usuarioinfo")
    private InformacionNutricional usuarioinfo;


    public Usuario(Long id_usuario, String usuario, String contrasena, String correo, Integer puntos, List<Habitos> usuariohabi, List<Marcaje> marcaje, InformacionNutricional usuarioinfo) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.puntos = puntos;
        this.usuariohabi = usuariohabi;
        this.marcaje = marcaje;
        this.usuarioinfo = usuarioinfo;
    }

    public Usuario() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return usuario;
    }
    @Override
    public String getPassword() {
        return contrasena;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
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
        return habitos;
    }

    public void setUsuariohabi(List<Habitos> habitos) {
        this.habitos = habitos;
    }

    public String getUsuario() {
        return usuario;
    }

    public List<Marcaje> getMarcaje() {
        return marcaje;
    }

    public void setMarcaje(List<Marcaje> marcaje) {
        this.marcaje = marcaje;



    public InformacionNutricional getUsuarioinfo() {
        return usuarioinfo;
    }

    public void setUsuarioinfo(InformacionNutricional usuarioinfo) {
        this.usuarioinfo = usuarioinfo;

    }
}
