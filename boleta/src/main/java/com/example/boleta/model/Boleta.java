package com.example.boleta.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_boleta;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;

    @Column(name = "id_venta", nullable = false)
    private int id_venta;
}
