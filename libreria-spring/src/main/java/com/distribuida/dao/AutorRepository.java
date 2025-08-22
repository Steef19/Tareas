package com.distribuida.dao;

import com.distribuida.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,Integer> {
    Autor findByNombre(String nomber);
    Autor findByCorreo(String correo);
    Autor findByApellido(String apellido);
}
