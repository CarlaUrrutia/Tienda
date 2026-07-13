
-- Estructura de tabla para la tabla `venta`

CREATE TABLE IF NOT EXISTS venta  (
    id_venta  int(11) NOT NULL,
    fecha_venta  date NOT NULL,
    id_cliente  int(11) NOT NULL,
    id_empleado  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




