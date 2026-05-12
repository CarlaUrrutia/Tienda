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
@Table(name = "envio")
public class Envio {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_envio;
    @Column(name = "fecha_envio")
    /*Se debe validar el date? */
    private Date fecha_envio;
    @Column(name = "fecha_estimada_entrega")
    /*Se debe validar el date? */
    private Date fecha_estimada_entrega;
    @Column(name = "estado",nullable = false)
    @NotNull(message = "La estado  no debe ser nulo")
    @NotEmpty(message = "La estado no debe ser nulo")
    @Min(value = 0)
    private String estado;
    @Column(name = "cantidad",nullable = false)
    @NotNull(message = "La Cantidad  no debe ser nula")
    @NotEmpty(message = "La Cantidad no debe ser nula")
    @Min(value = 0)
    private int cantidad;
    @Column(name = "direccion_destino",nullable = false)
    @NotNull(message = "La direccion de destino  no debe ser nulo")
    @NotEmpty(message = "La direccion de destino no debe ser nulo")
    private String direccion_destino;

    @ManyToOne
    @Column(name = "id_venta",nullable = false)
    private Venta id_venta;
    @ManyToOne
    @Column(name = "id_cliente",nullable = false)
    private Cliente id_cliente;
    @ManyToOne
    @Column(name = "id_empleado",nullable = false)  
    private Empleado id_empleado;
    @ManyToOne
    @Column(name = "id_ciudad",nullable = false)
    private Ciudad id_ciudad;
}
