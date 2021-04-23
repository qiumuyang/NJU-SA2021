package com.sa2021.stulist.excel;

import javax.sql.DataSource;

import com.sa2021.stulist.model.Student;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final String DataResourcePath = "excel/测试数据.xlsx";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemStreamReader<Student> reader() {
        PoiItemReader<Student> reader = new PoiItemReader<Student>();
        reader.setResource(new ClassPathResource(DataResourcePath));
        reader.setRowMapper(new RowMapperImpl());
        reader.setLinesToSkip(1);

        return reader;
    }

    @Bean
    public StudentItemProcessor processor() {
        return new StudentItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Student> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Student>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO students (id, name, department, phone) VALUES (:id, :name, :department, :phone)")
                .dataSource(dataSource) //
                .build();
    }

    @Bean
    public Job importJob(Step step1) {
        return jobBuilderFactory //
                .get("importJob") //
                .incrementer(new RunIdIncrementer()) //
                .flow(step1) //
                .end() //
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Student> writer) {
        return stepBuilderFactory //
                .get("step1") //
                .<Student, Student>chunk(10) //
                .reader(reader()) //
                .processor(processor()) //
                .writer(writer) //
                .build();
    }

}
