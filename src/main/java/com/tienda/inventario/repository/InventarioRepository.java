package com.tienda.inventario.repository;

import com.tienda.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    // Metodo derivado: Spring genera el SQL solo a partir del nombre.
    // Util para que Carrito pueda consultar "cuanto stock tiene el juego X"
    Optional<Inventario> findByJuegoId(Long juegoId);
}
