package com.example.gerente.model;

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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "producto")
public class Producto {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    @Column(name = "nombre",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error descripcion no valida")
    private String nombre;
    @Column(name = "precio_venta",nullable = false)
    @NotNull(message = "El precio de venta no debe ser nulo")
    @NotEmpty(message = "el precio de venta no debe ser nulo")
    @Min(value = 0)
    private int precio_venta;
    
    @ManyToOne
    @Column(name = "id_proveedor", nullable = false)
    private Proveedor id_proveedor;
}
