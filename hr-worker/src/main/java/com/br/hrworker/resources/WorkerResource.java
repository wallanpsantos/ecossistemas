package com.br.hrworker.resources;

import com.br.hrworker.entities.WorkerEntity;
import com.br.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Value("${hr-worker.config: Mock Local}")
    private String microConfig;

    @Autowired
    private Environment environment;

    @Autowired
    private WorkerRepository workerRepository;

    /**
     * Metodo responsavel retorna as configuracoes
     */
    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        logger.info("MICRO CONFIG = " + microConfig);
        return ResponseEntity.noContent().build();
    }

    /**
     * Metodo DEFAULT responsavel por retornar todos os dados da tabela workers
     */
    @GetMapping
    public ResponseEntity<List<WorkerEntity>> findAll() {
        List<WorkerEntity> listWorkerEntity = workerRepository.findAll();
        return ResponseEntity.ok(listWorkerEntity);
    }

    /**
     * Metodo responsavel por retornar um usuario da tabela worker
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerEntity> findById(@PathVariable Long id) {

        logger.info("PORT = " + environment.getProperty("local.server.port"));

        WorkerEntity workerEntity = workerRepository.findById(id).get();
        return ResponseEntity.ok(workerEntity);
    }
}
