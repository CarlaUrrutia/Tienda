package com.example.devolucion.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "devolucion")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_devolucion;

    @Column(name = "fecha_devolucion", nullable = false)
    private Date fecha_devolucion;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "monto_reembolso", nullable = false)
    private int monto_reembolso;

    @Column(name = "cantidad_devuelta", nullable = false)
    private int cantidad_devuelta;

    @Column(name = "id_empleado", nullable = false)
    private int id_empleado;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;

    @Column(name = "id_tarjeta", nullable = false)
    private int id_tarjeta;

    @Column(name = "id_venta", nullable = false)
    private int id_venta;

    @Column(name = "id_producto", nullable = false)
    private int id_producto;
}
