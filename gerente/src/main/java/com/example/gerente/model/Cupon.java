package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cupon")
public class Cupon {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cupon;
    @Column(name = "codigo",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50, message = "Error codigo no valido")
    private String codigo;
    @Column(name = "descuento",nullable = false)
    @NotNull(message = "El descuento no debe ser nulo")
    @NotEmpty(message = "el descuento no debe ser nulo")
    @Min(value = 0)
    private int descuento;
    @Column(name = "fecha_expiracion")
    private Date fecha_expiracion;
    /*Deben coincidir */
    @ManyToOne
    @Column(name = "id_cliente",nullable = false)
    private Cliente id_cliente;
}
