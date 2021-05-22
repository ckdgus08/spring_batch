package com.github.ckdgus08;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableBatchProcessing
@SpringBootApplication
@RequiredArgsConstructor
public class SpringBatchApplication {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
//
//    @Bean
//    public Step step1() {
//        return this.stepBuilderFactory.get("step1")
//                .tasklet(new HelloWorld())
//                .listener(promotionListener())
//                .build();
//    }

    @Bean
    public Step step2() {
        return this.stepBuilderFactory.get("step2")
                .tasklet(HelloWorldTasklet())
                .build();
    }

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(step2())
//                .next(step2())
//                .validator(validator())
//                .incrementer(new DailyJobTimeStamper())
//                .listener(JobListenerFactoryBean.getListener(
//                        new JobLoggerListener()))
                .build();
    }
//
//    @Bean
//    public StepExecutionListener promotionListener() {
//        ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
//
//        listener.setKeys(new String[]{"name"});
//        return listener;
//    }

    @Bean
    public Tasklet HelloWorldTasklet() {
        return new HelloWorld();
    }

    @Bean
    public CompositeJobParametersValidator validator() {
        CompositeJobParametersValidator validator =
                new CompositeJobParametersValidator();

        DefaultJobParametersValidator defaultJobParametersValidator
                = new DefaultJobParametersValidator(
                new String[]{"fileName"},
                new String[]{"name", "currentDate"});

        defaultJobParametersValidator.afterPropertiesSet();

        validator.setValidators(
                Arrays.asList(new ParameterValidator(),
                        defaultJobParametersValidator));

        return validator;
    }

}
