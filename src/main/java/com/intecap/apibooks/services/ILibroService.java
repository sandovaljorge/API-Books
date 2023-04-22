package com.intecap.apibooks.services;

import com.intecap.apibooks.models.Libro;
import com.intecap.apibooks.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {

    public ResponseEntity<LibroResponseRest> buscarLibros();
    public ResponseEntity<LibroResponseRest> buscarLibroById(int id);
    //public ResponseEntity<LibroResponseRest> buscarLibroByName(String nombre);
    public ResponseEntity<LibroResponseRest> guardarLibro(Libro libro);
    public ResponseEntity<LibroResponseRest> actualizarLibro(int id,Libro libro);
    public ResponseEntity<LibroResponseRest> eliminarLibro(int id);
}
