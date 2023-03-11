package com.intecap.apibooks.services;

import com.intecap.apibooks.models.Categoria;
import com.intecap.apibooks.response.CategoriaResponseRest;

public interface ICategoriaService {
    /**
     * Metodo para buscar categoria en la base de datos.
     * @return
     */
    public CategoriaResponseRest buscarCategorias();


}
