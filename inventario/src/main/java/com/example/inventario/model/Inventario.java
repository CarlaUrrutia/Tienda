package com.example.inventario.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_inventario;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "id_producto", nullable = false)
    private int id_producto;

    @Column(name = "id_tienda", nullable = false)
    private int id_tienda;
}
