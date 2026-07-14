

# Tienda - Sistema de Gestión de Tienda (Microservicios)

Proyecto desarrollado con Spring Boot siguiendo una arquitectura basada en microservicios. El sistema permite administrar clientes, productos, ventas, facturas, inventario, cupones y demás procesos relacionados con una tienda.

---

# Tecnologías utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Cloud OpenFeign
- MySQL
- Maven
- XAMPP (para MySQL)
- Visual Studio Code / IntelliJ IDEA

### Opcional

- Docker
- Docker Compose

---

# Microservicios

El proyecto está dividido en los siguientes servicios:

| Microservicio | Función |
|--------------|---------|
| Cliente | Administración de clientes |
| Empleado | Gestión de empleados |
| Producto | Gestión de productos |
| Inventario | Control de stock |
| Venta | Registro de ventas |
| DetalleVenta | Detalle de productos vendidos |
| Factura | Gestión de facturas |
| Boleta | Gestión de boletas |
| Cupón | Administración de cupones |
| Oferta | Administración de ofertas |
| Proveedor | Gestión de proveedores |
| Envío | Gestión de despachos |
| Devolución | Registro de devoluciones |
| Tarjeta | Administración de tarjetas |
| Rol | Gestión de roles |
| Tienda | Información de la tienda |

---

# Base de datos

El proyecto utiliza MySQL.

Antes de ejecutar los microservicios debes:

1. Iniciar MySQL desde XAMPP.
2. Crear las bases de datos correspondientes.
3. Importar los scripts SQL incluidos en la carpeta:

```
BD/
```

o

```
BBDD/
```

(según la versión utilizada).

---

# Configuración

Cada microservicio posee su propio archivo:

```
src/main/resources/application.properties
```

Verificar especialmente:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

---

# Cómo ejecutar el proyecto

## 1. Clonar el repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

---

## 2. Abrir el proyecto

Puedes utilizar:

- Visual Studio Code
- IntelliJ IDEA

---

## 3. Levantar MySQL

Desde XAMPP iniciar:

- Apache (opcional)
- MySQL

---

## 4. Importar la base de datos

Ejecutar los scripts SQL.

---

## 5. Ejecutar los microservicios

Se recomienda iniciar los servicios en el siguiente orden:

1. Rol
2. Cliente
3. Empleado
4. Proveedor
5. Producto
6. Inventario
7. Oferta
8. Cupón
9. Venta
10. DetalleVenta
11. Factura
12. Boleta
13. Envío
14. Devolución
15. Tienda

Esperar que cada servicio termine de iniciar antes de ejecutar el siguiente.

---

# Comunicación entre microservicios

La comunicación se realiza mediante Spring Cloud OpenFeign.

Cada servicio consume los recursos necesarios desde otros microservicios utilizando clientes Feign.

---

# Docker (Opcional)

El proyecto incluye archivos Docker para facilitar el despliegue.

No es necesario utilizar Docker para ejecutar el sistema localmente.

Si deseas utilizar Docker:

```bash
docker compose up --build
```

o ejecutar cada Dockerfile individualmente.

---

# Estructura del proyecto

```
Tienda
│
├── cliente
├── empleado
├── producto
├── inventario
├── venta
├── detalleVenta
├── factura
├── boleta
├── cupon
├── oferta
├── proveedor
├── envio
├── devolucion
├── tarjeta
├── rol
├── tienda
├── BD
└── BBDD
```

---

# Integrantes

- Nombre Integrante 1
- Nombre Integrante 2
- Nombre Integrante 3

---

# Licencia

Proyecto desarrollado con fines académicos.