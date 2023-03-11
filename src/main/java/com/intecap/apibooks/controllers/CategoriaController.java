package com.intecap.apibooks.controllers;

//import com.intecap.apibooks.services.CategoriaService;
import com.intecap.apibooks.response.CategoriaResponseRest;
import com.intecap.apibooks.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    /**
     * Metodo para listar las categorias.
     * @return
     */
    @GetMapping("/")
    public CategoriaResponseRest getCategorias(){
        return categoriaService.buscarCategorias();
    }

}
