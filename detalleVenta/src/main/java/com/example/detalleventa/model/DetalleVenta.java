package com.example.detalleventa.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario_venta")
    private int precio_unitario_venta;

    @Column(name = "id_venta", nullable = false)
    private int id_venta;

    @Column(name = "id_producto", nullable = false)
    private int id_producto;
}
