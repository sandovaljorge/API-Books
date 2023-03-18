package com.intecap.apibooks.services;

import com.intecap.apibooks.models.Categoria;
import com.intecap.apibooks.models.dao.ICategoriaDao;
import com.intecap.apibooks.response.CategoriaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoriaService implements ICategoriaService{

    //logger
    private static final Logger log = Logger.getLogger(CategoriaService.class.getName());

    /**
     * Inyectamos la interfaz para obtener la data
     */
    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        log.info("Buscando todas las categorias de la base de datos.");
        CategoriaResponseRest response =new CategoriaResponseRest();
        try{
            List<Categoria> listCategorias=(List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(listCategorias);
            response.setMetadata("Respuesta OK","200", "Lista de categorias.");

        }catch (Exception e){
            log.info("Error al consultar, Categorias."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategoriaById(int id) {
        log.info("Buscando categoria de la base de datos por id.");
        CategoriaResponseRest response =new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try{
            Optional<Categoria> categoria = categoriaDao.findById(id);
            if(categoria.isPresent()){
                list.add(categoria.get());
                response.getCategoriaResponse().setCategorias(list);
                response.setMetadata("Respuesta Ok", "200", "Categoria encontrada");
            }else{
                response.setMetadata("Respuesta Null", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.info("Error al consultar, Categorias."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> guardarCategoria(Categoria categoria) {
        log.info("Guardando categoria iniciada.");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list =new ArrayList<>();
        try{
            Categoria categoriaSave = categoriaDao.save(categoria);
            if(categoriaSave != null){
                list.add(categoriaSave);
                response.getCategoriaResponse().setCategorias(list);
                response.setMetadata("Respuesta Ok","200", "Categoria Creada");
            }else{
                response.setMetadata("Respuesta Null", "-1", "Error al crear categoria");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.info("Error al crear, categoria."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(int id, Categoria categoria) {
        log.info("Actualizando categoria iniciada.");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list =new ArrayList<>();
        try{
            Optional<Categoria> findCategoria = categoriaDao.findById(id);
            if(findCategoria.isPresent()){
                findCategoria.get().setNombre(categoria.getNombre());
                findCategoria.get().setDescripcion(categoria.getDescripcion());
                Categoria categoriaUpdate = categoriaDao.save(findCategoria.get());
                if(categoriaUpdate != null){
                    list.add(categoriaUpdate);
                    response.getCategoriaResponse().setCategorias(list);
                    response.setMetadata("Respuesta Ok","200", "Categoria Actualizada");
                }else{
                    response.setMetadata("Respuesta Nula","-1", "Error al actualizar la categoria");
                    return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                response.setMetadata("Respuesta Nula","-1", "Error categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error al actualizar categoria."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(int id) {
        log.info("Eliminando categoria iniciada.");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list =new ArrayList<>();
        try{
            Optional<Categoria> findCategoria = categoriaDao.findById(id);
            if(findCategoria.isPresent()){
                categoriaDao.delete(findCategoria.get());
                list.add(findCategoria.get());
                response.getCategoriaResponse().setCategorias(list);
                response.setMetadata("Respuesta Ok","200", "Categoria eliminada");
            }else{
                response.setMetadata("Respuesta Nula","-1", "Error categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error al actualizar categoria."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta error al eliminar.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }


}
