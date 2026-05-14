
-- Estructura de tabla para la tabla `ciudad`

CREATE TABLE IF NOT EXISTS ciudad (
    id_ciudad int(11) NOT NULL,
    nombre varchar(50) NOT NULL,
    id_region int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
