
-- Estructura de tabla para la tabla `devolucion`

CREATE TABLE IF NOT EXISTS devolucion (
    id_devolucion int(11) NOT NULL,
    fecha_devolucion date NOT NULL,
    motivo varchar(200) NOT NULL,
    monto_reembolso int(11) NOT NULL,
    cantidad_devuelta int(11) NOT NULL,
    id_empleado int(11) NOT NULL,
    id_cliente int(11) NOT NULL,
    id_tarjeta int(11) NOT NULL,
    id_venta int(11) NOT NULL,
    id_producto int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
