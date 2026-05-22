package com.example.devolucion.client;

import com.example.devolucion.dto.EmpleadoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "empleado", url = "${ms.empleado.url}")
public interface EmpleadoClient {
    @GetMapping("/api/empleados/{id}")
    EmpleadoResponse getEmpleadoById(@PathVariable("id") Integer id);
}
