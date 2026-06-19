package com.example.venta.client;

import com.example.venta.dto.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente", url = "${ms.cliente.url}")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ClienteResponse getClienteById(@PathVariable("id") Integer id);
}