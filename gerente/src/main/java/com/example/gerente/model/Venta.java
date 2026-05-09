package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta;
    @Column(name = "fecha_venta", nullable = false)
    private Date fecha_venta;
    
    @ManyToOne
    @Column(name = "id_cliente",nullable = false)
    private Cliente id_cliente;
    @ManyToOne
    @Column(name = "id_empleado",nullable = false)
    private Empleado id_empleado;
}
