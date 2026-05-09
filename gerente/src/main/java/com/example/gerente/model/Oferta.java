package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;
    @Column(name = "descuento",nullable = false)
    private double descuento; // 0.1 = 10%

    @ManyToOne
    @Column(name = "id_producto",nullable = false)
    private Producto id_producto;
}
