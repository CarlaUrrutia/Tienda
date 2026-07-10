package com.example.envio.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_envio;

    @Column(name = "fecha_envio", nullable = false)
    private Date fecha_envio;

    @Column(name = "fecha_estimada_entrega", nullable = false)
    private Date fecha_estimada_entrega;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "direccion_destino", nullable = false)
    private String direccion_destino;

    @Column(name = "id_venta", nullable = false)
    private int id_venta;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;

    @Column(name = "id_empleado", nullable = false)
    private int id_empleado;

    @Column(name = "id_ciudad", nullable = false)
    private int id_ciudad;
}
