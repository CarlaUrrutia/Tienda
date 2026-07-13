package com.example.detalleventa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Valid
@Data
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle;

    @Column(name = "cantidad", nullable = false)
    @NotNull(message = "La cantidad no debe ser nula")
    @Min(value = 0)
    private Integer cantidad;

    @Column(name = "precio_unitario_venta")
    @NotNull(message = "El precio no debe ser nulo")
    @Min(value = 0)
    private Integer precio_unitario_venta;

    @Column(name = "id_venta", nullable = false)
    private Integer id_venta;

    @Column(name = "id_producto", nullable = false)
    private Integer id_producto;
}