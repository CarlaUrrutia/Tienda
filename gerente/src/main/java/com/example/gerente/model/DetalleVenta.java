package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;
    @Column(name = "cantidad",nullable = false)
    private int cantidad; //` int(11) DEFAULT NULL,
    @Column(name = "precio_unitario_venta")
    private int precio_unitario_venta; //` int(11) DEFAULT NULL

    @OneToMany
    @Column(name = "id_venta",nullable = false)
    private Venta id_venta;
    @ManyToOne
    @Column(name = "id_producto",nullable = false)
    private Producto id_producto;
    
}
