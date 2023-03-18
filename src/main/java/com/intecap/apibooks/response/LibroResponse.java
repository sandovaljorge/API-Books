package com.intecap.apibooks.response;

import com.intecap.apibooks.models.Libro;

import java.util.List;

public class LibroResponse {

    private List<Libro> libros;

    public List<Libro> getLibros() {
        return this.libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
