package com.intecap.apibooks.services;

import com.intecap.apibooks.models.Categoria;
import com.intecap.apibooks.models.dao.ICategoriaDao;
import com.intecap.apibooks.response.CategoriaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CategoriaService implements ICategoriaService{

    //logger
    private static final Logger log = Logger.getLogger(CategoriaService.class.getName());

    /**
     * Inyectamos la interfaz para obtener la data
     */
    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseRest buscarCategorias() {
        log.info("Buscando todas las categorias de la base de datos.");
        CategoriaResponseRest response =new CategoriaResponseRest();
        try{
            List<Categoria> listCategorias=(List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(listCategorias);
            response.setMetadata("Respuesta OK","200", "Lista de categorias.");

        }catch (Exception e){
            log.info("Error al consultar, Categorias."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return response;
        }
        return response;
    }
}
