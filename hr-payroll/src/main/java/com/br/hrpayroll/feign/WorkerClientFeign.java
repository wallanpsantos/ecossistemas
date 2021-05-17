package com.br.hrpayroll.feign;

import com.br.hrpayroll.entities.WorkerEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hr-worker", path = "/workers")
public interface WorkerClientFeign {

    /**
     * Metodo responsavel por retornar um trabalhador
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerEntity> findBy(@PathVariable Long id);
}