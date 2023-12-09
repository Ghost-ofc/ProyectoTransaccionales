package com.Vitalife.Vitalife.DTO;

import com.Vitalife.Vitalife.entity.InformacionNutricional;
import com.Vitalife.Vitalife.entity.Marcaje;
import com.Vitalife.Vitalife.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class InformacionNutricionalDTO {

    private Long id_informacion;
    private String nombre;
    private String descripcion;
    private TituloDTO tituloinfo;
    private List<MarcajeDTO> marcajeinfo;
    private List<UsuarioDTO> usuarioinfo;

    public InformacionNutricionalDTO() {
    }

    public InformacionNutricionalDTO(Long id_informacion, String nombre, String descripcion, TituloDTO tituloinfo, List<MarcajeDTO> marcajeinfo, List<UsuarioDTO> usuarioinfo) {
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

    public TituloDTO getTituloinfo() {
        return tituloinfo;
    }

    public void setTituloinfo(TituloDTO tituloinfo) {
        this.tituloinfo = tituloinfo;
    }

    public List<MarcajeDTO> getMarcajeinfo() {
        return marcajeinfo;
    }

    public void setMarcajeinfo(List<MarcajeDTO> marcajeinfo) {
        this.marcajeinfo = marcajeinfo;
    }

    public List<UsuarioDTO> getUsuarioinfo() {
        return usuarioinfo;
    }

    public void setUsuarioinfo(List<UsuarioDTO> usuarioinfo) {
        this.usuarioinfo = usuarioinfo;
    }

    public InformacionNutricionalDTO toDTO(InformacionNutricional informacionNutricional){
        InformacionNutricionalDTO informacionNutricionalDTO = new InformacionNutricionalDTO();
        informacionNutricionalDTO.setId_informacion(informacionNutricional.getId_informacion());
        informacionNutricionalDTO.setNombre(informacionNutricional.getNombre());
        informacionNutricionalDTO.setDescripcion(informacionNutricional.getDescripcion());

        return informacionNutricionalDTO;
    }
}
