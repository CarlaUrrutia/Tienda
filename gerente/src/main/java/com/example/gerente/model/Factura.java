package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha",nullable = false)
    private Date fecha;
    @Column(name = "total",nullable = false)
    private double total;

    
    @OneToOne
    @Column(name = "venta",nullable = false)
    private Venta venta;
    @OneToOne
    @Column(name = "id_cliente",nullable = false)
    private Cliente id_cliente;
}
