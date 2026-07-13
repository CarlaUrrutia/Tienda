
-- Estructura de tabla para la tabla `inventario`

CREATE TABLE IF NOT EXISTS inventario  (
    id_inventario  int(11) NOT NULL,
    cantidad  int(11) NOT NULL,
    id_producto  int(11) NOT NULL,
    id_tienda  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
