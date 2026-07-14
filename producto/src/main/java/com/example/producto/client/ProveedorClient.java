package com.example.producto.client;

import com.example.producto.DTO.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "proveedor", url = "${ms.proveedor.url}")
public interface ProveedorClient {
    @GetMapping("/api/proveedores/{id}")
    ProveedorResponse getProveedorById(@PathVariable("id") Integer id);
}
