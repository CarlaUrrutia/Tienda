package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "devolucion")
public class Devolucion {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_devolucion;
    @Column(name = "fecha_devolucion")
    private Date fecha_devolucion;
    @Column(name = "motivo",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error motivo no valido")
    private String motivo;
    @Column(name = "monto_reembolso",nullable = false)
    @NotNull(message = "El monto no debe ser nulo")
    @NotEmpty(message = "El monto no debe ser nulo")
    @Min(value = 0)
    private int monto_reembolso;
    @Column(name = "cantidad_devuelta",nullable = false)
    @NotNull(message = "La cantidad no debe ser nula")
    @NotEmpty(message = "La cantidad no debe ser nula")
    @Min(value = 0)
    private int cantidad_devuelta;

    @ManyToOne
    @Column(name = "id_empleado", nullable = false)
    private Empleado id_empleado;
    @OneToOne
    @Column(name = "id_detalle_venta", nullable = false)
    private DetalleVenta id_detalle_venta;

    /*
    private Cliente id_cliente;
    private Tarjeta id_tarjeta;
    private Venta id_venta;
    private Producto id_producto;
    */

    
}
