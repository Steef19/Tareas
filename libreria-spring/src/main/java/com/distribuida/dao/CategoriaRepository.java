package com.distribuida.dao;

import com.distribuida.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByCategoria(String categoria);
}
