package com.br.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /**
     * Instância unica Singleton.
     * Metodo de configuração RestTemplate
     *
     * @return new RestTemplate();
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
