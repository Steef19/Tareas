package com.distribuida.controller;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.EntityReference;
import java.util.List;

@RestController
@RequestMapping("/api/libros")

public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> findAll(){
        List<Libro> libros = libroService.findAll();
        return ResponseEntity.ok(libros);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> findOne(@PathVariable int id){
        Libro libro = libroService.findOne(id);
        if (libro ==  null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);

    }
    @PostMapping
    public ResponseEntity<Libro> save(@RequestBody Libro libro){
        Libro libroNuevo = libroService.save(libro);
        return ResponseEntity.ok(libroNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable int id, @RequestBody Libro libro){
        int idCtegoria = libro.getCategoria().getIdCategoria();
        int idAutor = libro.getAutor().getIdAutor();

        Libro libroActualizado = libroService.update(id, idCtegoria, idAutor, libro);
        if (libroActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }





}