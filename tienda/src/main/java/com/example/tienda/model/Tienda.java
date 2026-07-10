package com.example.tienda.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tienda")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tienda;

    @Column(name = "nombre_tienda", nullable = false)
    private String nombre_tienda;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "horario_apertura")
    private Date horario_apertura;

    @Column(name = "politicas")
    private String politicas;
}
