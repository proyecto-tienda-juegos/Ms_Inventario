package com.tienda.inventario.controller;

import com.tienda.inventario.model.Inventario;
import com.tienda.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // GET http://localhost:8083/api/inventario
    @GetMapping
    public ResponseEntity<List<Inventario>> listarTodos() {
        return ResponseEntity.ok(inventarioService.listarTodos());
    }

    // GET http://localhost:8083/api/inventario/1
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        return inventarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET http://localhost:8083/api/inventario/juego/5
    // Usado por Carrito para validar stock antes de agregar un juego
    @GetMapping("/juego/{juegoId}")
    public ResponseEntity<Inventario> buscarPorJuegoId(@PathVariable Long juegoId) {
        return inventarioService.buscarPorJuegoId(juegoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST http://localhost:8083/api/inventario
    // Body (JSON): { "juegoId": 1, "cantidadDisponible": 50, "stockMinimo": 5 }
    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody Inventario inventario) {
        Inventario creado = inventarioService.crear(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // PUT http://localhost:8083/api/inventario/1
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @Valid @RequestBody Inventario inventario) {
        return inventarioService.actualizar(id, inventario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE http://localhost:8083/api/inventario/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = inventarioService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
