package com.intecap.apibooks.services;

import com.intecap.apibooks.models.Categoria;
import com.intecap.apibooks.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    /**
     * Metodo para buscar categoria en la base de datos.
     * @return
     */
    public ResponseEntity<CategoriaResponseRest> buscarCategorias();
    public ResponseEntity<CategoriaResponseRest> buscarCategoriaById(int id);
    public ResponseEntity<CategoriaResponseRest> guardarCategoria(Categoria categoria);
    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(int id, Categoria categoria);
    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(int id);

}
