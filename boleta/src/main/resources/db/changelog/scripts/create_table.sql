/*
--como esta en el video guia

CREATE TABLE IF NOT EXISTS empleado{
    run_emp INT PRIMARY KEY AUTO_INCREMENT,
    nom_emp VARCHAR(20),
    ape_emp VARCHAR(50),
    sueldo INT
}

--como fue creado

CREATE TABLE `ciudad` (
  `id_ciudad` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `id_region` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
*/

-- Estructura de tabla para la tabla `boleta`

CREATE TABLE IF NOT EXISTS boleta (
    id_boleta int(11) NOT NULL,
    id_cliente int(11) NOT NULL,
    id_venta int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `ciudad`

CREATE TABLE IF NOT EXISTS ciudad (
    id_ciudad int(11) NOT NULL,
    nombre varchar(50) NOT NULL,
    id_region int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `cliente`

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente int(11) NOT NULL,
    nombre varchar(100) NOT NULL,
    apellido varchar(100) NOT NULL,
    email varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `cupon`

CREATE TABLE IF NOT EXISTS cupon (
    id_cupon int(11) NOT NULL,
    codigo varchar(50) NOT NULL,
    descuento int(11) NOT NULL,
    fecha_expiracion date DEFAULT NULL,
    id_cliente int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `cupon`

CREATE TABLE IF NOT EXISTS cupon (
    id_cupon int(11) NOT NULL,
    codigo varchar(50) NOT NULL,
    descuento int(11) NOT NULL,
    fecha_expiracion date DEFAULT NULL,
    id_cliente int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `detalle_venta`

CREATE TABLE IF NOT EXISTS detalle_venta (
    id_detalle int(11) NOT NULL,
    cantidad int(11) NOT NULL,
    precio_unitario_venta int(11) DEFAULT NULL,
    id_venta int(11) NOT NULL,
    id_producto int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- Estructura de tabla para la tabla `empleado`

CREATE TABLE IF NOT EXISTS empleado (
    id_empleado int(11) NOT NULL,
    nombre varchar(100) NOT NULL,
    apellido varchar(100) NOT NULL,
    sueldo int(11) NOT NULL,
    id_tienda int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- Estructura de tabla para la tabla `factura`

CREATE TABLE IF NOT EXISTS factura  (
    id_factura  int(11) NOT NULL,
    fecha  date NOT NULL,
    total  int(11) NOT NULL,
    id_venta  int(11) NOT NULL,
    id_cliente  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `inventario`

CREATE TABLE IF NOT EXISTS inventario  (
    id_inventario  int(11) NOT NULL,
    cantidad  int(11) NOT NULL,
    id_producto  int(11) NOT NULL,
    id_tienda  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `oferta`

CREATE TABLE IF NOT EXISTS oferta  (
    id_oferta  int(11) NOT NULL,
    descripcion  varchar(100) NOT NULL,
    descuento  int(11) NOT NULL,
    id_producto  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `producto`

CREATE TABLE IF NOT EXISTS producto  (
    id_producto  int(11) NOT NULL,
    nombre  varchar(100) NOT NULL,
    precio_venta  int(11) NOT NULL,
    id_proveedor  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `proveedor`

CREATE TABLE IF NOT EXISTS proveedor  (
    id_proveedor  int(11) NOT NULL,
    nombre  varchar(100) NOT NULL,
    contacto  varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- Estructura de tabla para la tabla `region`

CREATE TABLE IF NOT EXISTS region  (
    id_region  int(11) NOT NULL,
    nombre  varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `rol`

CREATE TABLE IF NOT EXISTS rol  (
    id_rol  int(11) NOT NULL,
    nombre_rol  varchar(50) NOT NULL,
    id_empleado  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `tarjeta`

CREATE TABLE IF NOT EXISTS tarjeta  (
    id_tarjeta  int(11) NOT NULL,
    tipo char(1) NOT NULL,
    id_cliente  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `tienda`

CREATE TABLE IF NOT EXISTS tienda  (
    id_tienda  int(11) NOT NULL,
    nombre_tienda  varchar(100) NOT NULL,
    ubicacion  varchar(100) NOT NULL,
    horario_apertura  date DEFAULT NULL,
    politicas  varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `venta`

CREATE TABLE IF NOT EXISTS venta  (
    id_venta  int(11) NOT NULL,
    fecha_venta  date NOT NULL,
    id_cliente  int(11) NOT NULL,
    id_empleado  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




