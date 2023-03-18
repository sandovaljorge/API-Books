package com.intecap.apibooks.controllers;

//import com.intecap.apibooks.services.CategoriaService;
import com.intecap.apibooks.models.Categoria;
import com.intecap.apibooks.response.CategoriaResponseRest;
import com.intecap.apibooks.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CategoriaResponseRest> getCategorias(){
        return categoriaService.buscarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseRest> getCategoriaById(@PathVariable("id") int id){
        return categoriaService.buscarCategoriaById(id);
    }

    @PostMapping("/")
    public ResponseEntity<CategoriaResponseRest> saveCategoria(@RequestBody Categoria categoria){
        return categoriaService.guardarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseRest> updateCategoria(@PathVariable("id") int id,@RequestBody Categoria categoria){
        return categoriaService.actualizarCategoria(id,categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaResponseRest> deleteCategoria(@PathVariable("id") int id){
        return categoriaService.eliminarCategoria(id);
    }
}
