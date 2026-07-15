package com.example.oferta.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_oferta;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "descuento", nullable = false)
    private int descuento;

    @Column(name = "id_producto", nullable = false)
    private int id_producto;
}
