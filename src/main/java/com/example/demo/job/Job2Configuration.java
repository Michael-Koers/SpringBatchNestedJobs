package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Job2Configuration {

    @Autowired
    private HelloWorldTasklet helloWorldTasklet;

    @Bean
    public Job job2(JobRepository jobRepository, Step job3step1, Step job3step2, Step job3step3) {
        return new JobBuilder("job2", jobRepository)
                .start(job3step1)
                .next(job3step2)
                .next(job3step3)
                .build();
    }

    @Bean
    public Step job2step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("job2-step1", jobRepository)
                .tasklet(helloWorldTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step job2step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("job2-step2", jobRepository)
                .tasklet(helloWorldTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step job2step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("job2-step3", jobRepository)
                .tasklet(helloWorldTasklet, transactionManager)
                .build();
    }
}
