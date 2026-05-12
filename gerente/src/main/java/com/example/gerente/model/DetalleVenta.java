package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "detalle_venta")
public class DetalleVenta {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;
    @Column(name = "cantidad",nullable = false)
    @NotNull(message = "La Cantidad  no debe ser nula")
    @NotEmpty(message = "La Cantidad no debe ser nula")
    @Min(value = 0)
    private int cantidad; //` int(11) DEFAULT NULL,
    @Column(name = "precio_unitario_venta")
    @NotNull(message = "El Precio  no debe ser nulo")
    @NotEmpty(message = "El Precio no debe ser nulo")
    @Min(value = 0)
    private int precio_unitario_venta; //` int(11) DEFAULT NULL
    /*Confirmar luego la clave foranea  */
    @OneToMany
    @Column(name = "id_venta",nullable = false)
    private Venta id_venta;
    /*Confirmar luego la clave foranea */
    @ManyToOne
    @Column(name = "id_producto",nullable = false)
    private Producto id_producto;
    
}
