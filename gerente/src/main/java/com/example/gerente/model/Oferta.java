package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Oferta {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_oferta;
    @Column(name = "descripcion",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error descripcion no valida")
    private String descripcion;
    @Column(name = "descuento",nullable = false)
    @NotNull(message = "El descuento no debe ser nulo")
    @NotEmpty(message = "el descuento no debe ser nulo")
    @Min(value = 0)
    private int descuento; // 0.1 = 10%
    /*clavesita */
    @ManyToOne
    @Column(name = "id_producto",nullable = false)
    private Producto id_producto;
}
