package com.tienda.inventario.service;

import com.tienda.inventario.model.Inventario;
import com.tienda.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> buscarPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    public Optional<Inventario> buscarPorJuegoId(Long juegoId) {
        return inventarioRepository.findByJuegoId(juegoId);
    }

    public Inventario crear(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Optional<Inventario> actualizar(Long id, Inventario datosNuevos) {
        return inventarioRepository.findById(id).map(existente -> {
            existente.setJuegoId(datosNuevos.getJuegoId());
            existente.setCantidadDisponible(datosNuevos.getCantidadDisponible());
            existente.setStockMinimo(datosNuevos.getStockMinimo());
            return inventarioRepository.save(existente);
        });
    }

    public boolean eliminar(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
