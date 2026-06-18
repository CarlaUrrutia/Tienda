package com.example.empleado.client;

import com.example.empleado.dto.RolResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rol", url = "${ms.rol.url}")
public interface RolClient {
    @GetMapping("/api/roles/{id}")
    RolResponse getRolById(@PathVariable("id") Integer id);
}