package com.example.tarjeta.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tarjeta")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tarjeta;

    @Column(name = "tipo", nullable = false)
    private char tipo;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;
}
