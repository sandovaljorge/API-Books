package com.intecap.apibooks.services;

import com.intecap.apibooks.models.Libro;
import com.intecap.apibooks.models.dao.ILibroDao;
import com.intecap.apibooks.response.CategoriaResponseRest;
import com.intecap.apibooks.response.LibroResponseRest;
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
public class LibroService implements ILibroService{
    private static final Logger log = Logger.getLogger(LibroService.class.getName());

    @Autowired
    private ILibroDao libroDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        log.info("Listando los libros.");
        LibroResponseRest response = new LibroResponseRest();
        try{
            List<Libro> listLibros = (List<Libro>) libroDao.findAll();
            response.getLibroResponse().setLibros(listLibros);
            response.setMetadata("Respuesta OK","200", "Lista de libros.");
        }catch (Exception e){
            log.info("Error al consultar los libros."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibroById(int id) {
        log.info("Listando los libros.");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();
        try{
            Optional<Libro> libro = libroDao.findById(id);
            if(libro.isPresent()){
                list.add(libro.get());
                response.getLibroResponse().setLibros(list);
                response.setMetadata("Respuesta Ok", "200", "Libro encontrado");
            }else{
                response.setMetadata("Respuesta Null", "-1", "Libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error al consultar los libros."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> guardarLibro(Libro libro) {
        log.info("Guardando un libro.");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();
        try{
            Libro libroSave = libroDao.save(libro);
            if(libroSave != null){
                list.add(libroSave);
                response.getLibroResponse().setLibros(list);
                response.setMetadata("Respuesta Ok", "200", "Libro creado.");
            }else{
                response.setMetadata("Respuesta Null", "-1", "Error Libro no creado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.info("Error al insertar un libro."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Mala Respuesta.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> actualizarLibro(int id, Libro libro) {
        log.info("Actualizando un libro.");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();
        try{
            Optional<Libro> findLibro = libroDao.findById(id);
            if(findLibro.isPresent()){
                findLibro.get().setNombre(libro.getNombre());
                findLibro.get().setDescripcion(libro.getDescripcion());
                findLibro.get().setCategoria(libro.getCategoria());
                Libro libroUpdate = libroDao.save(findLibro.get());
                if(libroUpdate != null){
                    list.add(libroUpdate);
                    response.getLibroResponse().setLibros(list);
                    response.setMetadata("Respuesta Ok", "200", "Libro actualizado.");
                }else{
                    response.setMetadata("Respuesta Nula","-1", "Error al actualizar un libro");
                    return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                response.setMetadata("Respuesta Nula","-1", "Error libro no encontrada");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error al actualizar un libro."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Mala Respuesta.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> eliminarLibro(int id) {
        log.info("Eliminando un libro.");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();
        try{
            Optional<Libro> findLibro = libroDao.findById(id);
            if(findLibro.isPresent()){
                libroDao.delete(findLibro.get());
                list.add(findLibro.get());
                response.getLibroResponse().setLibros(list);
                response.setMetadata("Respuesta Ok", "200", "Libro eliminado.");
            }else{
                response.setMetadata("Respuesta Nula","-1", "Error libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error al eliminar un libro."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Mala Respuesta.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }
}
