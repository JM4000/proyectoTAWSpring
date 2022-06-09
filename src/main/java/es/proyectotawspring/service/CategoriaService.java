/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.CategoriaRepository;
import es.proyectotawspring.dto.CategoriaDTO;
import es.proyectotawspring.entity.CategoriaEntity;
import es.proyectotawspring.entity.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static es.proyectotawspring.entity.CategoriaEntity.toDTOList;

/**
 *
 * @author juanm
 */
@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    private void setUsuarioRepository(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaEntity> toEntityList(List<CategoriaDTO> lista){
        List<CategoriaEntity> result = new ArrayList<>();
        for(CategoriaDTO c:lista){
            result.add(this.categoriaRepository.getById(c.getIdCategoria()));
        }

        return result;
    }

    public List<CategoriaDTO> getCategoriasLike(String busqueda) {

        List<CategoriaEntity> result;

        result = this.categoriaRepository.findAllByNombreLike(busqueda);

        return toDTOList(result);

    }

    public List<CategoriaEntity> getCategorias(List<String> busqueda) {

        List<CategoriaEntity> result = new ArrayList<>();

        for (String s: busqueda) {
            result.add(this.categoriaRepository.findByNombre(s));
        }

        return result;

    }

    public void crearCategoria(String str) {

        if (null != str) {
            CategoriaEntity categoria = new CategoriaEntity();
            categoria.setNombre(str);

            this.categoriaRepository.save(categoria);
        }
    }

    public List<CategoriaDTO> findAll() {
        return toDTOList(this.categoriaRepository.findAll());
    }

    public void editarCategoria(Integer id, String edit) {

        if (id != null && edit != null) {
            CategoriaEntity cat = this.categoriaRepository.findById(id).orElse(null);
            cat.setNombre(edit);
            this.categoriaRepository.save(cat);
        }
    }

    public CategoriaDTO find(int id) {
        return this.categoriaRepository.findById(id).orElse(null).toDTO();
    }

    public void remove(int id) {
        this.categoriaRepository.delete((this.categoriaRepository.findById(id).orElse(null)));
    }

    public List<CategoriaEntity> createlistFromIds(String[] categorias) {

        
         
        List<CategoriaEntity> categoriasFinales = new ArrayList<>();
        if (categorias != null) {
            for (String categoria : categorias) {
                categoriasFinales.add(this.categoriaRepository.findById(Integer.parseInt(categoria)).orElse(null));
            }
        }
    
        return categoriasFinales;
    }

    public List<CategoriaEntity> getCategoriasRestantes(List<CategoriaEntity> categoriasFinales) {

        List<CategoriaEntity> result = this.categoriaRepository.findAll();
        result.removeAll(categoriasFinales);

        return result;
    }

    public void modificarCategorias(List<CategoriaEntity> categoriasFinales, List<CategoriaEntity> categoriasNoIncluidas, ProductoEntity producto) {

        for(CategoriaEntity c:categoriasFinales){
            if(!c.getProductoList().contains(producto)){
                c.getProductoList().add(producto);
                this.categoriaRepository.save(c);
            }
        }

        for(CategoriaEntity c:categoriasNoIncluidas){
            if(c.getProductoList().contains(producto)){
                c.getProductoList().remove(producto);
                this.categoriaRepository.save(c);
            }
        }

    }
}
