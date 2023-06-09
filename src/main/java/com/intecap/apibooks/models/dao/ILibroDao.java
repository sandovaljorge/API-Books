package com.intecap.apibooks.models.dao;

import com.intecap.apibooks.models.Libro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILibroDao extends CrudRepository<Libro, Integer> {

}
