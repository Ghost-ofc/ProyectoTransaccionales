package com.Vitalife.Vitalife.DTO;

import com.Vitalife.Vitalife.entity.Habitos;
import com.Vitalife.Vitalife.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
    private Long id_usuario;
    private String usuario;
    private String contrasena;
    private String correo;
    private Integer puntos;
    private List<HabitosDTO> usuariohabi;

    public UsuarioDTO(){

    }
    public UsuarioDTO(Long id_usuario, String usuario, String contrasena, String correo, Integer puntos, List<HabitosDTO> usuariohabi) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.puntos = puntos;
        this.usuariohabi = usuariohabi;
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

    public List<HabitosDTO> getUsuariohabi() {
        return usuariohabi;
    }

    public void setUsuariohabi(List<HabitosDTO> usuariohabi) {
        this.usuariohabi = usuariohabi;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId_usuario(usuario.getId_usuario());
        dto.setUsuario(usuario.getUsuario());
        dto.setContrasena(usuario.getContrasena());
        dto.setCorreo(usuario.getCorreo());
        dto.setPuntos(usuario.getPuntos());

        if (usuario.getUsuariohabi() != null && !usuario.getUsuariohabi().isEmpty()) {
            List<HabitosDTO> habitosDTOList = new ArrayList<>();
            for (Habitos habito : usuario.getUsuariohabi()) {
                HabitosDTO habitosDTO = new HabitosDTO();
                habitosDTO.setId_habito(habito.getId_habito());
                habitosDTO.setNombreHabito(habito.getNombreHabito());
                // Configura otras propiedades de HabitosDTO según tus necesidades
                habitosDTOList.add(habitosDTO);
            }
            dto.setUsuariohabi(habitosDTOList);
        }

        return dto;
    }
}
