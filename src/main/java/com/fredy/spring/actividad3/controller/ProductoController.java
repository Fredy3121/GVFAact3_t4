package com.fredy.spring.actividad3.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fredy.spring.actividad3.entity.Categoria;
import com.fredy.spring.actividad3.entity.Producto;
import com.fredy.spring.actividad3.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Endpoints auxiliares de Categoria
    @PostMapping("/categorias")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(productoService.guardarCategoria(categoria), HttpStatus.CREATED);
    }

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
        return productoService.listarCategorias();
    }

    // --- CRUD DE PRODUCTO ---

    // 1. LEER (Todos)
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoService.listarTodos();
    }

    // 1b. LEER (Por ID)
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // 2. CREAR
    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, 
                                                  @RequestParam Long categoriaId) {
        Producto guardado = productoService.guardarProducto(producto, categoriaId);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    // 3. ACTUALIZAR
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,
                                                       @RequestBody Producto producto,
                                                       @RequestParam(required = false) Long categoriaId) {
        try {
            Producto actualizado = productoService.actualizarProducto(id, producto, categoriaId);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. ELIMINAR
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
    try {
        productoService.eliminarProducto(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto eliminado exitosamente con ID: " + id);

        return ResponseEntity.ok(respuesta);

    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
}