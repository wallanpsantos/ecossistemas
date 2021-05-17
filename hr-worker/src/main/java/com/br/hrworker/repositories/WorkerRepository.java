package com.br.hrworker.repositories;

import com.br.hrworker.entities.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {

}
