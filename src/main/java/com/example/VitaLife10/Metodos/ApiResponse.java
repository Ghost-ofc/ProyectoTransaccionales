package com.example.VitaLife10.Metodos;

import com.example.VitaLife10.entity.Usuario;

public class ApiResponse {

    private String message;
    private Usuario usuario;

    public ApiResponse(String message, Usuario usuario) {
        this.message = message;
        this.usuario = usuario;
    }



    public String getMessage() {
        return message;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
