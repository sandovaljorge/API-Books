package com.intecap.apibooks.models.dao;

import com.intecap.apibooks.models.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria, Integer> {
}
