/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.CategoriaRepository;
import es.proyectotawspring.dao.ListaRepository;
import es.proyectotawspring.dao.UsuarioRepository;
import es.proyectotawspring.dto.ListaDTO;
import es.proyectotawspring.entity.CategoriaEntity;
import es.proyectotawspring.entity.ListaEntity;
import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static es.proyectotawspring.entity.ListaEntity.toDTOList;

/**
 *
 * @author Agustín
 */
@Service
public class ListaService {

    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;
    private ListaRepository listaRepository;

    @Autowired
    private void setCategoriaRepository(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Autowired
    private void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private void setListaRepository(ListaRepository listaRepository){
        this.listaRepository = listaRepository;
    }
    
    public List<ListaDTO> getListasLike(String busqueda) {

        List<ListaDTO> listas;

        if (busqueda != null) {
            listas = toDTOList(this.listaRepository.findAllByNombreLike(busqueda));
        } else {
            listas = toDTOList(this.listaRepository.findAll());
        }

        return listas;
    }

    public List<ListaDTO> findAll() {
        return toDTOList(this.listaRepository.findAll());
    }

    public void editarLista(Integer id, String edit) {
        if (id != null && edit != null) {
            ListaEntity lista = this.listaRepository.findById(id).orElse(null);
            lista.setNombre(edit);
            this.listaRepository.save(lista);
        }
    }

    public void crearLista(String nombre, String categoria) {
        if (null != nombre) {
            ListaEntity lista = new ListaEntity();
            lista.setNombre(nombre);
            CategoriaEntity nuevaCategoria = this.categoriaRepository.findAllByNombre(categoria).get(0);
            lista.setCategoriaidCategoria(nuevaCategoria);
            this.listaRepository.save(lista);
        }
    }

    public ListaDTO find(int id) {
        return this.listaRepository.findById(id).orElse(null).toDTO();
    }

    public void removeUsuarioFromLista(int idusuario, int idlista) {
        ListaEntity lista = this.listaRepository.findById(idlista).orElse(null);
        UsuarioEntity usuario = this.usuarioRepository.findById(idusuario).orElse(null);
        List <UsuarioEntity> usuarios = lista.getUsuarioList();
        usuarios.remove(usuario);
        this.listaRepository.save(lista);
    }

    public void addUsuarioToLista(int idusuario, int idlista) {
        ListaEntity lista = this.listaRepository.findById(idlista).orElse(null);
        UsuarioEntity usuario = this.usuarioRepository.findById(idusuario).orElse(null);
        List <UsuarioEntity> usuarios = lista.getUsuarioList();
        usuarios.add(usuario);
        this.listaRepository.save(lista);
    }

    public void remove(int id) {
        this.listaRepository.delete((this.listaRepository.findById(id)).orElse(null));
    }
    
}
