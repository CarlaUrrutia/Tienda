
-- Estructura de tabla para la tabla `envio`

CREATE TABLE IF NOT EXISTS envio (
    id_envio int(11) NOT NULL,
    fecha_envio date NOT NULL,
    fecha_estimada_entrega date NOT NULL,
    estado varchar(50) NOT NULL,
    direccion_destino varchar(200) NOT NULL,
    id_venta int(11) NOT NULL,
    id_cliente int(11) NOT NULL,
    id_empleado int(11) NOT NULL,
    id_ciudad int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
