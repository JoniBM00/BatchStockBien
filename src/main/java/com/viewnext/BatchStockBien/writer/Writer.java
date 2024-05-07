package com.viewnext.BatchStockBien.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import com.viewnext.BatchStockBien.model.Producto;

@Component
public class Writer {

	@Bean
	public FlatFileItemWriter<Producto> stockWriter() {

		return new FlatFileItemWriterBuilder<Producto>().name("productoItemWriter")
				.resource(new PathResource("stockTerminales.csv")).delimited()
				.names("lugar", "id", "stock", "stockReal", "stockVirtual").build();

	}

}
