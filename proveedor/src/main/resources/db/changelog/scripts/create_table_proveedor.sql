
-- Estructura de tabla para la tabla `proveedor`

CREATE TABLE IF NOT EXISTS proveedor  (
    id_proveedor  int(11) NOT NULL,
    nombre  varchar(100) NOT NULL,
    contacto  varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


