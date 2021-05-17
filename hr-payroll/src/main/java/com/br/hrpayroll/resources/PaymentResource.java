package com.br.hrpayroll.resources;

import com.br.hrpayroll.entities.PaymentEntity;
import com.br.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<PaymentEntity> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        PaymentEntity paymentEntity = paymentService.getPaymentEntity(workerId, days);
        return ResponseEntity.ok(paymentEntity);
    }

    /**
     * Chamada alternativa caso o metodo getPayment() com o caminho /{workerId}/days/{days} esteja fora do ar
     * O Dev junto ao negocio devem decidi qual o caminho seguir nessa caso
     * No Caso estou criando uma instancia com valores fixos para teste
     */
    public ResponseEntity<PaymentEntity> getPaymentAlternative(Long workerId, Integer days) {
        PaymentEntity paymentEntity = new PaymentEntity("Brann", 400.0, days);
        return ResponseEntity.ok(paymentEntity);
    }
}