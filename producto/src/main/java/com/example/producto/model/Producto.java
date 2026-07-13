package com.example.producto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Valid
@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    @Column(name = "nombre", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error descripcion no valida")
    private String nombre;

    @Column(name = "precio_venta", nullable = false)
    @NotNull(message = "El precio de venta no debe ser nulo")
    @Min(value = 0)
    private Integer precio_venta;

    @Column(name = "id_proveedor", nullable = false)
    private Integer id_proveedor;
}