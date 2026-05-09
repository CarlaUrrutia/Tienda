package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Valid
@Entity
@Table(name = "tirnda")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tienda;
    @Column(name = "nombre_tienda",nullable = false)
    private String nombre_tienda;
    @Column(name = "ubicacion",nullable = false)
    private String ubicacion;

    private Date horario_aprertura;

    private String politicas;


    private Empleado id_empleado;
}
