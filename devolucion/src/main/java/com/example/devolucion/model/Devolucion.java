package com.example.devolucion.model;

import java.sql.Date;

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
@Table(name = "devolucion")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_devolucion;

    @Column(name = "fecha_devolucion")
    private Date fecha_devolucion;

    @Column(name = "motivo", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error motivo no valido")
    private String motivo;

    @Column(name = "monto_reembolso", nullable = false)
    @NotNull(message = "El monto no debe ser nulo")
    @Min(value = 0)
    private Integer monto_reembolso;

    @Column(name = "cantidad_devuelta", nullable = false)
    @NotNull(message = "La cantidad no debe ser nula")
    @Min(value = 0)
    private Integer cantidad_devuelta;

    @Column(name = "id_empleado", nullable = false)
    private Integer id_empleado;

    @Column(name = "id_cliente", nullable = false)
    private Integer id_cliente;

    @Column(name = "id_tarjeta", nullable = false)
    private Integer id_tarjeta;

    @Column(name = "id_venta", nullable = false)
    private Integer id_venta;

    @Column(name = "id_producto", nullable = false)
    private Integer id_producto;
}