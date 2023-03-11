package com.intecap.apibooks.response;

import com.intecap.apibooks.models.Categoria;

import java.util.List;

public class CategoriaResponse {
    private List<Categoria> categorias;

    public List<Categoria> getCategorias(){
        return this.categorias;
    }

    public void setCategorias(List<Categoria> categorias){
        this.categorias = categorias;
    }
}
