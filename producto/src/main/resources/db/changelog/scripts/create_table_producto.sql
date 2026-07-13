
-- Estructura de tabla para la tabla `producto`

CREATE TABLE IF NOT EXISTS producto  (
    id_producto  int(11) NOT NULL,
    nombre  varchar(100) NOT NULL,
    precio_venta  int(11) NOT NULL,
    id_proveedor  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
