package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Valid
@Data
@Entity
@Table(name = "venta")
public class Venta {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta;
    @Column(name = "fecha_venta", nullable = false)
    private Date fecha_venta;
    @ManyToOne
    @Column(name = "id_cliente",nullable = false)
    private Cliente id_cliente;
    @ManyToOne
    @Column(name = "id_empleado",nullable = false)
    private Empleado id_empleado;

    /*
    debatible si falta o no
    total 
    estado
    */
}
