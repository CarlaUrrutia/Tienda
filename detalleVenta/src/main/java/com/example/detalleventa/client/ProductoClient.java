package com.example.detalleventa.client;

import com.example.detalleventa.dto.ProductoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto", url = "${ms.producto.url}")
public interface ProductoClient {
    @GetMapping("/api/productos/{id}")
    ProductoResponse getProductoById(@PathVariable("id") Integer id);
}
