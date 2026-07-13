
-- Estructura de tabla para la tabla `factura`

CREATE TABLE IF NOT EXISTS factura  (
    id_factura  int(11) NOT NULL,
    fecha  date NOT NULL,
    total  int(11) NOT NULL,
    id_venta  int(11) NOT NULL,
    id_cliente  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
