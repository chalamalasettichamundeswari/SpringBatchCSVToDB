package com.springboot.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springboot.batch.model.Student;

@Configuration
public class BatchConfig {
   @Autowired
   StepBuilderFactory sbf;
   @Autowired
   JobBuilderFactory jbf;
   
    @Bean
	public Step step() {
		return sbf.get("step1")
				.<Student, Student>chunk(3)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
   
    @Bean
	public Job job() {
		return jbf.get("job1")
				.incrementer(new RunIdIncrementer())
				.start(step())
				.build();
	}

	
   @Bean
   public ItemReader<Student> reader(){
	FlatFileItemReader<Student> reader =  new FlatFileItemReader<>();
	reader.setResource(new ClassPathResource("students.csv"));
	DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
	DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	lineTokenizer.setNames("id","name","testscore");
	BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	fieldSetMapper.setTargetType(Student.class);
	lineMapper.setLineTokenizer(lineTokenizer);
	lineMapper.setFieldSetMapper(fieldSetMapper);
	reader.setLineMapper(lineMapper);
	return reader;	   
   }
   
   @Bean
   public ItemProcessor<Student,Student> processor(){
	return (s)->{
		s.setTestscore(s.getTestscore()*9.5/100);
		return s;
	};
   } 
	@Bean 
	 public ItemWriter<Student> writer(){
		JdbcBatchItemWriter<Student> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Student>());
		writer.setSql("INSERT INTO STUDENT (ID,NAME,TESTSCORE) VALUES(:id,:name,:testscore)");
		writer.setDataSource(dataSource());
		return writer;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/employee_database");
		dataSource.setUsername("root");
		dataSource.setPassword("Durgamma@505");
		return dataSource;
	}
     
}
