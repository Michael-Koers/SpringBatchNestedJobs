package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class NestedJobsConfiguration {

    @Autowired
    @Qualifier("job1")
    private Job job1;
    @Autowired
    @Qualifier("job2")
    private Job job2;

    @Autowired
    @Qualifier("job3")
    private Job job3;

    @Bean
    public Job nestedJobs(JobRepository jobRepository, Step nestedJob1, Step nestedJob2, Step nestedJob3) {
        return new JobBuilder("nestedJobs", jobRepository)
                .start(nestedJob1)
                .next(nestedJob2)
                .next(nestedJob3)
                .build();
    }

    @Bean
    public Step nestedJob1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("nestedJob1", jobRepository)
                .job(job1)
                .build();
    }

    @Bean
    public Step nestedJob2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("nestedJob2", jobRepository)
                .job(job2)
                .build();
    }

    @Bean
    public Step nestedJob3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("nestedJob3", jobRepository)
                .job(job3)
                .build();
    }
}
