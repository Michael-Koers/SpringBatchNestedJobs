package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBatchNestedJobsApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(SpringBatchNestedJobsApplication.class, args);

        // The reason we need to manually launch these jobs is because, as soon as you overwrite anything from the
        // DefaultBatchConfiguration, auto-startup backs off, and you need to find a different way to start your job(s).
        // See the following stackoverflow posts:
        //  https://stackoverflow.com/questions/63208994/how-to-run-spring-batch-job-through-commandlinerunner-if-i-am-using-xml-based-c
        //  https://stackoverflow.com/questions/75626317/spring-batch-5-does-not-start-job-automatically
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        JobParameters parameters = new JobParametersBuilder()
                .addString("LocalDateTime", LocalDateTime.now().toString())
                .toJobParameters();

        // Launching each of these separately works fine
        Job job1 = context.getBean("job1", Job.class);
        jobLauncher.run(job1, parameters);

        Job job2 = context.getBean("job2", Job.class);
        jobLauncher.run(job2, parameters);

        Job job3 = context.getBean("job3", Job.class);
        jobLauncher.run(job3, parameters);

        // Looking nested jobs.... also works fine
        Job nestedJob = context.getBean("nestedJobs", Job.class);
        jobLauncher.run(nestedJob, parameters);
    }
}
