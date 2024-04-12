package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing(executionContextSerializerRef = "jackson")
public class BatchConfiguration {

    // Override default context serializer (Base64) to jackson (JSON)
    @Bean
    public ExecutionContextSerializer jackson() {
        return new Jackson2ExecutionContextStringSerializer();
    }
}
