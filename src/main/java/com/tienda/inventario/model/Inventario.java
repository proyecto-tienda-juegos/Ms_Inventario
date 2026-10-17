package com.tienda.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // No guardamos el juego completo aqui, solo su id.
    // El detalle del juego (titulo, genero, precio) vive en juegos-service;
    // si se necesita se consulta alla por REST, no se duplica la info.
    @NotNull(message = "El id del juego es obligatorio")
    @Column(name = "juego_id", nullable = false)
    private Long juegoId;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @PositiveOrZero(message = "La cantidad disponible no puede ser negativa")
    @Column(name = "cantidad_disponible", nullable = false)
    private Integer cantidadDisponible;

    @PositiveOrZero(message = "El stock minimo no puede ser negativo")
    @Column(name = "stock_minimo")
    private Integer stockMinimo;

    public Inventario() {
    }

    public Inventario(Long juegoId, Integer cantidadDisponible, Integer stockMinimo) {
        this.juegoId = juegoId;
        this.cantidadDisponible = cantidadDisponible;
        this.stockMinimo = stockMinimo;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
