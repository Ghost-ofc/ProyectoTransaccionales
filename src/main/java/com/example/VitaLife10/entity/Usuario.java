package com.example.VitaLife10.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
//Se crea la tabla de usuarios
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "email")
    private String correo;
    @Column(name = "points")
    private int puntos;


    @ManyToMany
    @JoinTable(
            name = "usuarios_titulos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "titulo_id")
    )
    @JsonIgnoreProperties("usuarios")
    private List<Titulo> titulos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("usuario")
    private List<HabitoUsuario> habitosUsuario = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_habitos_sistema",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "habito_sistema_id")
    )
    @JsonIgnoreProperties("usuarioH")
    private List<HabitoSistema> habitosSistema = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public List<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<Titulo> titulos) {
        this.titulos = titulos;
    }

    public List<HabitoUsuario> getHabitosUsuario() {
        return habitosUsuario;
    }

    public void setHabitosUsuario(List<HabitoUsuario> habitosUsuario) {
        this.habitosUsuario = habitosUsuario;
    }

    public List<HabitoSistema> getHabitosSistema() {
        return habitosSistema;
    }

    public void setHabitosSistema(List<HabitoSistema> habitosSistema) {
        this.habitosSistema = habitosSistema;
    }
}
