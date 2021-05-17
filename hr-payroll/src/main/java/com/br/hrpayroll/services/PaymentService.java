package com.br.hrpayroll.services;

import com.br.hrpayroll.entities.PaymentEntity;
import com.br.hrpayroll.entities.WorkerEntity;
import com.br.hrpayroll.feign.WorkerClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentService {

    @Autowired
    private WorkerClientFeign workerClientFeign;

    public PaymentEntity getPaymentEntity(long workerId, int days) {

        WorkerEntity workerEntity = workerClientFeign.findBy(workerId).getBody();

        if (!Objects.isNull(workerEntity)) {
            return new PaymentEntity(workerEntity.getName(), workerEntity.getDailyIncome(), days);
        }
        throw new IllegalArgumentException("Erro no retorno da entidade de pagamento");
    }
}
