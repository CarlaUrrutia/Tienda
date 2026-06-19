package com.example.venta.client;

import com.example.venta.dto.EmpleadoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "empleado", url = "${ms.empleado.url}")
public interface EmpleadoClient {

    @GetMapping("/api/empleados/{id}")
    EmpleadoResponse getEmpleadoById(@PathVariable("id") Integer id);
}