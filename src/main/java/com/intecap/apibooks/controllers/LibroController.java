package com.intecap.apibooks.controllers;

import com.intecap.apibooks.models.Libro;
import com.intecap.apibooks.response.LibroResponseRest;
import com.intecap.apibooks.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("libro")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.POST})
public class LibroController {

    @Autowired
    private ILibroService libroService;

    @GetMapping("/")
    public ResponseEntity<LibroResponseRest> getLibros(){
        return libroService.buscarLibros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseRest> getLibroById(@PathVariable("id") int id){
        return libroService.buscarLibroById(id);
    }

    @PostMapping("/")
    public ResponseEntity<LibroResponseRest> saveLibro(@RequestBody Libro libro){
        return libroService.guardarLibro(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponseRest> updateLibro(@PathVariable("id") int id,@RequestBody Libro libro){
        return libroService.actualizarLibro(id,libro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LibroResponseRest> deleteLibro(@PathVariable("id") int id){
        return libroService.eliminarLibro(id);
    }
}
