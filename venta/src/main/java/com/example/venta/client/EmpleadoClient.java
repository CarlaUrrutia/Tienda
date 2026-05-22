package com.example.venta.client;

import com.example.venta.DTO.EmpleadoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client que llama al microservicio empleado.
 * Cuando VentaService necesita los datos del empleado,
 * Feign hace automáticamente GET http://localhost:8081/api/empleados/{id}
 */
@FeignClient(name = "empleado", url = "${ms.empleado.url}")
public interface EmpleadoClient {

    @GetMapping("/api/empleados/{id}")
    EmpleadoResponse getEmpleadoById(@PathVariable("id") Integer id);
}
