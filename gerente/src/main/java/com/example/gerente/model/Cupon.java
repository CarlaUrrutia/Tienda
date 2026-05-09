package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cupon")
public class Cupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cupon;
    @Column(name = "codigo",nullable = false)
    private String codigo;
    @Column(name = "descuento",nullable = false)
    private int descuento;
    @Column(name = "fecha_expiracion")
    private Date fecha_expiracion;
    
    @ManyToOne
    @Column(name = "id_cliente",nullable = false)
    private Cliente id_cliente;
}
