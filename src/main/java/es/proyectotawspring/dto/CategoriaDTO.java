/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.dto;

/**
 *
 * @author juanm
 */
public class CategoriaDTO {
    
    private static final long serialVersionUID = 1L;
    private Integer idCategoria;
    private String nombre;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriaDTO(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
