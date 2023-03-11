package com.intecap.apibooks.response;

public class CategoriaResponseRest extends Response{
    /**
     * Response contiene la estructura de la metadata
     */

    /**
     * Devuelve el listado de categorias
     */
    private CategoriaResponse categoriaResponse = new CategoriaResponse();
    public CategoriaResponse getCategoriaResponse(){
        return this.categoriaResponse;
    }

    public void setCategoriaResponse(CategoriaResponse categoriaResponse){
        this.categoriaResponse = categoriaResponse;
    }
}
