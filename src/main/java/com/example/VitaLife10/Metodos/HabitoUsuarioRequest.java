package com.example.VitaLife10.Metodos;

public class HabitoUsuarioRequest {
    private String nombre;
    private boolean grupo;
    private String nombreUsuario;

    private int puntos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isGrupo() {
        return grupo;
    }

    public void setDiario(boolean diario) {
        this.grupo = diario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
