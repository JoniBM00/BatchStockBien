package com.viewnext.BatchStockBien.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.viewnext.BatchStockBien.listener.JobCompletionNotificationListener;
import com.viewnext.BatchStockBien.model.Producto;
import com.viewnext.BatchStockBien.process.ProductoItemProcessor;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ProductoSteps {

	private FlatFileItemWriter<Producto> writer;
	private FlatFileItemReader<Producto> reader;
	private ProductoItemProcessor processor;

	/**
	 * Retorna un objeto JobCompletionNotificationListener
	 * 
	 * @return un JobCompletionNotificationListener
	 */
	@Bean
	public JobCompletionNotificationListener listener() {
		return new JobCompletionNotificationListener();
	}

	/**
	 * @param jobRepository
	 * @param step1
	 * @param listener
	 * @return Un Job
	 */
	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
		return new JobBuilder("importUserJob", jobRepository).listener(listener).start(step1).build();
	}

	/**
	 * Lee y escribe en .csv
	 * 
	 * @param jobRepository
	 * @param transactionManager
	 * @return Un Step
	 */
	@Bean
	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager) {

		return new StepBuilder("step1", jobRepository).<Producto, Producto>chunk(10, transactionManager).reader(reader)
				.processor(processor).writer(writer).build();
	}

}
