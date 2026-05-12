package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "factura")
public class Factura {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;
    @Column(name = "fecha",nullable = false)
    private Date fecha;
    @Column(name = "total",nullable = false)
    @NotNull(message = "El total no debe ser nulo")
    @NotEmpty(message = "el total no debe ser nulo")
    @Min(value = 0)
    private int total;
    @OneToOne
    @Column(name = "venta",nullable = false)
    private Venta venta;
    @OneToOne
    @Column(name = "id_cliente",nullable = false)
    private Cliente id_cliente;
}
