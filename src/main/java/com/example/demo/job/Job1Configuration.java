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
public class Job1Configuration {

    @Autowired
    private HelloWorldTasklet helloWorldTasklet;

    @Bean
    public Job job1(JobRepository jobRepository, Step job1step1, Step job1step2, Step job1step3) {
        return new JobBuilder("job1", jobRepository)
                .start(job1step1)
                .next(job1step2)
                .next(job1step3)
                .build();
    }

    @Bean
    public Step job1step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("job1-step1", jobRepository)
                .tasklet(helloWorldTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step job1step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("job1-step2", jobRepository)
                .tasklet(helloWorldTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step job1step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("job1-step3", jobRepository)
                .tasklet(helloWorldTasklet, transactionManager)
                .build();
    }
}
