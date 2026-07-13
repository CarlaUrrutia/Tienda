package com.example.envio.model;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_envio;

    @Column(name = "fecha_envio")
    private Date fecha_envio;

    @Column(name = "fecha_estimada_entrega")
    private Date fecha_estimada_entrega;

    @Column(name = "estado", nullable = false)
    @NotNull(message = "El estado no debe ser nulo")
    @NotEmpty(message = "El estado no debe ser nulo")
    private String estado;

    @Column(name = "cantidad", nullable = false)
    @NotNull(message = "La cantidad no debe ser nula")
    @Min(value = 0)
    private Integer cantidad;

    @Column(name = "direccion_destino", nullable = false)
    @NotNull(message = "La dirección de destino no debe ser nula")
    @NotEmpty(message = "La dirección de destino no debe ser nula")
    private String direccion_destino;

    @Column(name = "id_venta", nullable = false)
    private Integer id_venta;

    @Column(name = "id_cliente", nullable = false)
    private Integer id_cliente;

    @Column(name = "id_empleado", nullable = false)
    private Integer id_empleado;

    @Column(name = "id_ciudad", nullable = false)
    private Integer id_ciudad;
}